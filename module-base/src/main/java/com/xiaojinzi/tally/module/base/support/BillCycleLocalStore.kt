package com.xiaojinzi.tally.module.base.support

import com.xiaojinzi.module.common.base.spi.spPersistence
import com.xiaojinzi.support.ktx.AppScope
import com.xiaojinzi.support.ktx.MutableSharedStateFlow
import com.xiaojinzi.tally.lib.res.model.tally.BillCycleResDto
import com.xiaojinzi.tally.lib.res.model.tally.MoneyFen
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillInsertDto
import kotlinx.coroutines.flow.first
import org.json.JSONArray
import org.json.JSONObject
import java.util.Calendar

/**
 * 本地周期记账存储(开源版)。
 *
 * 原版周期记账完全依赖服务端, 开源版无后端, 这里用 SharedPreferences(JSON) 在本地存储周期规则,
 * 并在 App 启动时按 nextExecTime 自动生成账单。
 */
object BillCycleLocalStore {

    // 以 JSON 字符串形式持久化周期列表
    private val listJsonStateOb = MutableSharedStateFlow<String?>()
        .spPersistence(
            scope = AppScope,
            key = "localBillCycleListJson",
            def = null,
        )

    private fun BillCycleResDto.toJson(): JSONObject = JSONObject().apply {
        put("id", id)
        put("userId", userId)
        put("bookId", bookId)
        put("state", state)
        put("cycleType", cycleType)
        put("loopCount", loopCount)
        put("timeZone", timeZone)
        put("dayOfMonth", dayOfMonth)
        put("dayOfWeek", dayOfWeek)
        put("hour", hour)
        put("billType", billType)
        put("categoryId", categoryId)
        put("accountId", accountId)
        put("transferTargetAccountId", transferTargetAccountId)
        put("amount", amount.value)
        put("note", note)
        put("nextExecTime", nextExecTime)
    }

    private fun JSONObject.toBillCycle(): BillCycleResDto = BillCycleResDto(
        id = getLong("id"),
        userId = optString("userId"),
        bookId = optString("bookId"),
        state = optString("state"),
        cycleType = optString("cycleType"),
        loopCount = optInt("loopCount"),
        timeZone = optInt("timeZone"),
        dayOfMonth = optInt("dayOfMonth"),
        dayOfWeek = optInt("dayOfWeek"),
        hour = optInt("hour"),
        billType = optString("billType"),
        categoryId = optString("categoryId"),
        accountId = optString("accountId"),
        transferTargetAccountId = optString("transferTargetAccountId"),
        amount = MoneyFen(value = optLong("amount")),
        note = optString("note"),
        nextExecTime = optLong("nextExecTime"),
    )

    private suspend fun loadList(): List<BillCycleResDto> {
        val json = listJsonStateOb.first().orEmpty()
        if (json.isEmpty()) {
            return emptyList()
        }
        return runCatching {
            val array = JSONArray(json)
            (0 until array.length()).map { array.getJSONObject(it).toBillCycle() }
        }.getOrElse { emptyList() }
    }

    private suspend fun saveList(list: List<BillCycleResDto>) {
        val array = JSONArray()
        list.forEach { array.put(it.toJson()) }
        listJsonStateOb.emit(value = array.toString())
    }

    /**
     * 计算下一次执行的时间戳(>= after)
     */
    private fun computeNextExecTime(
        cycleType: String,
        hour: Int,
        dayOfMonth: Int,
        dayOfWeek: Int,
        after: Long,
    ): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = after
        calendar.set(Calendar.HOUR_OF_DAY, hour.coerceIn(0, 23))
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        when (cycleType) {
            BillCycleResDto.CYCLE_TYPE_DAY -> {
                if (calendar.timeInMillis <= after) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                }
            }

            BillCycleResDto.CYCLE_TYPE_WEEK -> {
                // dayOfWeek: 1=周一 ... 7=周日, 映射到 Calendar(1=周日)
                val targetCalendarDay = when (dayOfWeek) {
                    7 -> Calendar.SUNDAY
                    else -> dayOfWeek + 1
                }
                calendar.set(Calendar.DAY_OF_WEEK, targetCalendarDay)
                if (calendar.timeInMillis <= after) {
                    calendar.add(Calendar.WEEK_OF_YEAR, 1)
                }
            }

            BillCycleResDto.CYCLE_TYPE_MONTH -> {
                val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth.coerceIn(1, maxDay))
                if (calendar.timeInMillis <= after) {
                    calendar.add(Calendar.MONTH, 1)
                    val newMax = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth.coerceIn(1, newMax))
                }
            }
        }
        return calendar.timeInMillis
    }

    suspend fun getBillCycleList(): List<BillCycleResDto> = loadList()

    suspend fun getBillCycleById(id: Long): BillCycleResDto {
        return loadList().first { it.id == id }
    }

    suspend fun deleteBillCycleById(id: Long) {
        saveList(list = loadList().filterNot { it.id == id })
    }

    suspend fun setBillCycleState(id: Long, state: String): BillCycleResDto {
        val list = loadList()
        val updated = list.map { if (it.id == id) it.copy(state = state) else it }
        saveList(list = updated)
        return updated.first { it.id == id }
    }

    suspend fun createOrUpdateBillCycle(
        id: Long?,
        bookId: String,
        cycleType: String,
        loopCount: Int?,
        timeZone: Int,
        dayOfMonth: Int?,
        dayOfWeek: Int?,
        hour: Int,
        billType: String,
        categoryId: String?,
        accountId: String?,
        transferTargetAccountId: String?,
        amount: Long,
        note: String?,
    ): BillCycleResDto {
        val userId = AppServices.userSpi.requiredUserInfo().id
        val list = loadList().toMutableList()
        val theDayOfMonth = dayOfMonth ?: 1
        val theDayOfWeek = dayOfWeek ?: 1
        val nextExecTime = computeNextExecTime(
            cycleType = cycleType,
            hour = hour,
            dayOfMonth = theDayOfMonth,
            dayOfWeek = theDayOfWeek,
            after = System.currentTimeMillis(),
        )
        val result: BillCycleResDto
        val existIndex = id?.let { theId -> list.indexOfFirst { it.id == theId } } ?: -1
        if (existIndex >= 0) {
            // 更新
            result = list[existIndex].copy(
                bookId = bookId,
                cycleType = cycleType,
                loopCount = loopCount ?: 0,
                timeZone = timeZone,
                dayOfMonth = theDayOfMonth,
                dayOfWeek = theDayOfWeek,
                hour = hour,
                billType = billType,
                categoryId = categoryId.orEmpty(),
                accountId = accountId.orEmpty(),
                transferTargetAccountId = transferTargetAccountId.orEmpty(),
                amount = MoneyFen(value = amount),
                note = note.orEmpty(),
                nextExecTime = nextExecTime,
            )
            list[existIndex] = result
        } else {
            // 新建
            result = BillCycleResDto(
                id = System.currentTimeMillis(),
                userId = userId,
                bookId = bookId,
                state = BillCycleResDto.STATE_RUNNING,
                cycleType = cycleType,
                loopCount = loopCount ?: 0,
                timeZone = timeZone,
                dayOfMonth = theDayOfMonth,
                dayOfWeek = theDayOfWeek,
                hour = hour,
                billType = billType,
                categoryId = categoryId.orEmpty(),
                accountId = accountId.orEmpty(),
                transferTargetAccountId = transferTargetAccountId.orEmpty(),
                amount = MoneyFen(value = amount),
                note = note.orEmpty(),
                nextExecTime = nextExecTime,
            )
            list.add(result)
        }
        saveList(list = list)
        return result
    }

    /**
     * 立即执行一次: 直接生成一条账单
     */
    suspend fun runBillCycleOnce(id: Long): BillCycleResDto {
        val cycle = getBillCycleById(id = id)
        insertBillFromCycle(cycle = cycle, time = System.currentTimeMillis())
        return cycle
    }

    /**
     * App 启动时调用: 对所有 running 的周期, 把到期的都补上账单
     */
    suspend fun generateDueBills() {
        val now = System.currentTimeMillis()
        val list = loadList()
        if (list.isEmpty()) {
            return
        }
        val updatedList = list.map { cycle ->
            if (cycle.state != BillCycleResDto.STATE_RUNNING) {
                cycle
            } else {
                var nextExecTime = cycle.nextExecTime
                var remainLoop = cycle.loopCount
                // loopCount <= 0 视为无限循环
                var guard = 0
                while (nextExecTime in 1..now && guard < 366) {
                    insertBillFromCycle(cycle = cycle, time = nextExecTime)
                    nextExecTime = computeNextExecTime(
                        cycleType = cycle.cycleType,
                        hour = cycle.hour,
                        dayOfMonth = cycle.dayOfMonth,
                        dayOfWeek = cycle.dayOfWeek,
                        after = nextExecTime,
                    )
                    if (remainLoop > 0) {
                        remainLoop -= 1
                        if (remainLoop == 0) {
                            break
                        }
                    }
                    guard += 1
                }
                cycle.copy(
                    nextExecTime = nextExecTime,
                    state = if (cycle.loopCount > 0 && remainLoop == 0) {
                        BillCycleResDto.STATE_STOPPED
                    } else {
                        cycle.state
                    },
                    loopCount = if (cycle.loopCount > 0) remainLoop else cycle.loopCount,
                )
            }
        }
        saveList(list = updatedList)
    }

    private suspend fun insertBillFromCycle(cycle: BillCycleResDto, time: Long) {
        val userId = AppServices.userSpi.requiredUserInfo().id
        AppServices.tallyDataSourceSpi.insertBill(
            target = TallyBillInsertDto(
                userId = userId,
                bookId = cycle.bookId,
                type = TallyBillDto.Type.NORMAL.value,
                time = time,
                categoryId = cycle.categoryId.ifEmpty { null },
                accountId = cycle.accountId.ifEmpty { null },
                transferTargetAccountId = cycle.transferTargetAccountId.ifEmpty { null },
                amount = cycle.amount,
                note = cycle.note.ifEmpty { null },
            ),
            isNeedSync = true,
        )
    }

}
