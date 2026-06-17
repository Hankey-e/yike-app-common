package com.xiaojinzi.tally.module.main.module.setting.domain

import android.content.Context
import android.net.Uri
import androidx.annotation.UiContext
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCase
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCaseImpl
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.annotation.ViewModelLayer
import com.xiaojinzi.support.ktx.getDayInterval
import com.xiaojinzi.support.ktx.timeAtLeast
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.model.tally.MoneyFen
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import com.xiaojinzi.tally.module.base.spi.TallyDataSourceSpi
import com.xiaojinzi.tally.module.base.support.AppRouterUserApi
import com.xiaojinzi.tally.module.base.support.AppServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.math.roundToLong

sealed class SettingIntent {

    data object Submit : SettingIntent()

    data class ToLoginOut(
        @UiContext val context: Context,
    ) : SettingIntent()

    data class ToLogOff(
        @UiContext val context: Context,
    ) : SettingIntent()

    data class ImportCsv(
        @UiContext val context: Context,
        val uri: Uri,
    ) : SettingIntent()

    data class ExportCsv(
        @UiContext val context: Context,
        val uri: Uri,
    ) : SettingIntent()

}

@ViewModelLayer
interface SettingUseCase : BusinessMVIUseCase

@ViewModelLayer
class SettingUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
) : BusinessMVIUseCaseImpl(
    commonUseCase = commonUseCase,
), SettingUseCase {

    @IntentProcess
    private suspend fun submit(intent: SettingIntent.Submit) {
        // TODO
    }

    @IntentProcess
    private suspend fun toLoginOut(intent: SettingIntent.ToLoginOut) {
        confirmDialogOrError(
            content = "退出后不会删除任何历史数据, 下次登录依然可以使用本账号".toStringItemDto(),
        )
        showLoading()
        kotlin.runCatching {
            AppServices.userSpi.logoutForBusinessLogic()
        }
        hideLoading()
    }

    @IntentProcess
    private suspend fun toLogOff(intent: SettingIntent.ToLogOff) {
        // 去确认注销登录
        AppRouterUserApi::class
            .routeApi()
            .signOutConfirmBySuspend(context = intent.context)
        // 再次弹出一个框框进行确认
        confirmDialogOrError(
            title = "警告".toStringItemDto(),
            content = "注销后将不能恢复, 确认注销吗?".toStringItemDto(),
        )
        showLoading()
        try {
            timeAtLeast {
                // 调用注销登录的接口
                AppServices.appNetworkSpi.logOff()
            }
            tip(
                content = "注销成功".toStringItemDto(),
            )
            // 退出登录
            AppServices.userSpi.logoutForBusinessLogic()
        } finally {
            hideLoading()
        }
    }

    /**
     * 导入 CSV 账单数据(目前适配「鲨鱼记账」导出的明细格式)
     * 格式: 日期,收支类型,类别,金额,备注 (GBK 编码, 字段用引号包裹)
     * 数据导入到当前选中的账本, 类别按名称尽量匹配现有分类, 匹配不上则不绑定分类
     */
    @IntentProcess
    private suspend fun importCsv(intent: SettingIntent.ImportCsv) {
        showLoading()
        try {
            // 自动识别编码: UTF-8 BOM(本应用导出) → UTF-8; 否则按 GBK(鲨鱼记账导出)
            val text = withContext(Dispatchers.IO) {
                intent.context.contentResolver
                    .openInputStream(intent.uri)
                    ?.use { input ->
                        val bytes = input.readBytes()
                        val hasUtf8Bom = bytes.size >= 3 &&
                                bytes[0] == 0xEF.toByte() &&
                                bytes[1] == 0xBB.toByte() &&
                                bytes[2] == 0xBF.toByte()
                        if (hasUtf8Bom) {
                            String(bytes, 3, bytes.size - 3, charset("UTF-8"))
                        } else {
                            String(bytes, charset("GBK"))
                        }
                    }
            }
            if (text.isNullOrBlank()) {
                tip(content = "无法读取文件内容".toStringItemDto())
                return
            }

            val currentUserInfo = AppServices.userSpi.requiredUserInfo()
            val tallyDataSourceSpi = AppServices.tallyDataSourceSpi
            val currentBookInfo = tallyDataSourceSpi.requiredSelectedBookInfo()
            val categoryList = tallyDataSourceSpi.getCategoryByBookId(bookId = currentBookInfo.id)
            // 当前账本的收/支分类, 用于按名称匹配
            val spendingCategories = categoryList
                .filter { it.type == TallyCategoryDto.Companion.TallyCategoryType.SPENDING }
            val incomeCategories = categoryList
                .filter { it.type == TallyCategoryDto.Companion.TallyCategoryType.INCOME }

            // 去重: 用已存在账单的 (日期+金额+备注+分类) 指纹集合, 跳过重复导入
            val dedupKeySet = tallyDataSourceSpi
                .getBillDetailListByCondition(
                    queryCondition = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                        bookIdList = listOf(currentBookInfo.id),
                    ),
                )
                .map { detail ->
                    billDedupKey(
                        dayStart = getDayInterval(timeStamp = detail.core.time).first,
                        amountFen = detail.core.amount.value,
                        note = detail.core.note,
                        categoryId = detail.categoryAdapter?.id,
                    )
                }
                .toHashSet()

            val dateFormat = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
            val billInsertList = mutableListOf<TallyBillInsertDto>()
            var skipCount = 0
            var dupCount = 0

            // 跳过表头(第一行)
            text.split(Regex(pattern = "\\r?\\n"))
                .drop(n = 1)
                .forEach { rawLine ->
                    if (rawLine.isBlank()) {
                        return@forEach
                    }
                    val fields = parseCsvLine(line = rawLine)
                    if (fields.size < 4) {
                        skipCount++
                        return@forEach
                    }
                    val dateStr = fields[0]
                    val typeStr = fields[1]
                    val categoryName = fields.getOrNull(index = 2)?.takeIf { it.isNotBlank() }
                    val amountStr = fields.getOrNull(index = 3)
                    val note = fields.getOrNull(index = 4)?.takeIf { it.isNotBlank() }

                    val time = runCatching { dateFormat.parse(dateStr)?.time }.getOrNull()
                    val amountYuan = amountStr
                        ?.filter { it.isDigit() || it == '.' }
                        ?.toDoubleOrNull()
                    if (time == null || amountYuan == null) {
                        skipCount++
                        return@forEach
                    }

                    // 鲨鱼记账只有「支出」和「收入」两种, 非「收入」一律视为支出
                    val isSpending = typeStr != "收入"
                    // 金额以「分」存储, 支出为负数, 收入为正数
                    val absFen = amountYuan.times(other = 100.0).roundToLong().absoluteValue
                    val amountFen = if (isSpending) -absFen else absFen
                    val matchedCategory = categoryName?.let {
                        matchCategory(
                            name = it,
                            candidates = if (isSpending) spendingCategories else incomeCategories,
                        )
                    }

                    // 去重: 与已有账单或本次 CSV 内已处理的行重复则跳过
                    val dedupKey = billDedupKey(
                        dayStart = getDayInterval(timeStamp = time).first,
                        amountFen = amountFen,
                        note = note,
                        categoryId = matchedCategory?.id,
                    )
                    if (!dedupKeySet.add(dedupKey)) {
                        dupCount++
                        return@forEach
                    }

                    billInsertList.add(
                        element = TallyBillInsertDto(
                            userId = currentUserInfo.id,
                            bookId = currentBookInfo.id,
                            type = TallyBillDto.Type.NORMAL.value,
                            time = time,
                            categoryId = matchedCategory?.id,
                            amount = MoneyFen(
                                value = amountFen,
                            ),
                            note = note,
                        )
                    )
                }

            if (billInsertList.isEmpty()) {
                val emptyTip = if (dupCount > 0) {
                    "没有新增账单, 跳过 $dupCount 条重复"
                } else {
                    "没有可导入的有效数据"
                }
                tip(content = emptyTip.toStringItemDto())
                return
            }

            tallyDataSourceSpi.insertOrUpdateBillList(
                targetList = billInsertList,
                isNeedSync = true,
            )

            val tipContent = buildString {
                append("成功导入 ")
                append(billInsertList.size)
                append(" 条账单")
                if (dupCount > 0) {
                    append(", 跳过 ")
                    append(dupCount)
                    append(" 条重复")
                }
                if (skipCount > 0) {
                    append(", 忽略 ")
                    append(skipCount)
                    append(" 条无效")
                }
            }
            tip(content = tipContent.toStringItemDto())
        } finally {
            hideLoading()
        }
    }

    /**
     * 账单去重指纹: 日期(当天0点)+金额(分)+备注+分类
     */
    private fun billDedupKey(
        dayStart: Long,
        amountFen: Long,
        note: String?,
        categoryId: String?,
    ): String {
        return "$dayStart|$amountFen|${note.orEmpty()}|${categoryId.orEmpty()}"
    }

    /**
     * 导出当前账本的账单为 CSV(列: 日期,收支类型,类别,金额,备注), 与导入格式兼容。
     * 采用 UTF-8 BOM 编码, 方便 Excel 正确识别中文。
     */
    @IntentProcess
    private suspend fun exportCsv(intent: SettingIntent.ExportCsv) {
        showLoading()
        try {
            val tallyDataSourceSpi = AppServices.tallyDataSourceSpi
            val currentBookInfo = tallyDataSourceSpi.requiredSelectedBookInfo()
            val billList = tallyDataSourceSpi.getBillDetailListByCondition(
                queryCondition = TallyDataSourceSpi.Companion.BillQueryConditionDto(
                    bookIdList = listOf(currentBookInfo.id),
                ),
            )
            if (billList.isEmpty()) {
                tip(content = "当前账本没有可导出的账单".toStringItemDto())
                return
            }

            val dateFormat = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
            val csv = buildString {
                append("\"日期\",\"收支类型\",\"类别\",\"金额\",\"备注\"\r\n")
                billList.forEach { detail ->
                    val absFen = detail.core.amount.value.absoluteValue
                    val isSpending = detail.core.amount.value < 0
                    val amountStr = if (absFen % 100L == 0L) {
                        (absFen / 100L).toString()
                    } else {
                        String.format(Locale.US, "%.2f", absFen / 100.0)
                    }
                    val date = dateFormat.format(Date(detail.core.time))
                    val type = if (isSpending) "支出" else "收入"
                    val category = csvEscape(detail.categoryAdapter?.name.orEmpty())
                    val note = csvEscape(detail.core.note.orEmpty())
                    append("\"").append(date)
                        .append("\",\"").append(type)
                        .append("\",\"").append(category)
                        .append("\",\"").append(amountStr)
                        .append("\",\"").append(note)
                        .append("\"\r\n")
                }
            }

            withContext(Dispatchers.IO) {
                intent.context.contentResolver.openOutputStream(intent.uri)?.use { os ->
                    // UTF-8 BOM
                    os.write(byteArrayOf(0xEF.toByte(), 0xBB.toByte(), 0xBF.toByte()))
                    os.write(csv.toByteArray(charset = charset("UTF-8")))
                    os.flush()
                }
            }
            tip(content = "成功导出 ${billList.size} 条账单".toStringItemDto())
        } finally {
            hideLoading()
        }
    }

    /**
     * CSV 字段转义: 内部的双引号需要翻倍
     */
    private fun csvEscape(value: String): String {
        return value.replace(oldValue = "\"", newValue = "\"\"")
    }

    /**
     * 按名称匹配分类: 精确匹配优先, 其次「现有分类名包含 CSV 名」(如 餐饮 -> 食品餐饮),
     * 再次「CSV 名包含现有分类名」, 都匹配不上则返回 null(账单不绑定分类)
     */
    private fun matchCategory(
        name: String,
        candidates: List<TallyCategoryDto>,
    ): TallyCategoryDto? {
        candidates.firstOrNull { it.name == name }?.let { return it }
        candidates.firstOrNull { it.name?.contains(other = name) == true }?.let { return it }
        return candidates.firstOrNull {
            val candidateName = it.name
            !candidateName.isNullOrBlank() && name.contains(other = candidateName)
        }
    }

    /**
     * 解析一行 CSV: 字段用引号包裹并以英文逗号分隔(数据本身不含逗号/引号, 做简单解析)
     */
    private fun parseCsvLine(line: String): List<String> {
        val trimmed = line.trim()
        if (trimmed.isEmpty()) {
            return emptyList()
        }
        return trimmed
            .removePrefix(prefix = "\"")
            .removeSuffix(suffix = "\"")
            .split("\",\"")
            .map { it.trim() }
    }

    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}