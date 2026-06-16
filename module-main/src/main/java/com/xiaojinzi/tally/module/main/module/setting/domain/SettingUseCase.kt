package com.xiaojinzi.tally.module.main.module.setting.domain

import android.content.Context
import android.net.Uri
import androidx.annotation.UiContext
import com.xiaojinzi.component.impl.Router
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCase
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCaseImpl
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.annotation.ViewModelLayer
import com.xiaojinzi.support.ktx.timeAtLeast
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.QQ_GROUP_LINK
import com.xiaojinzi.tally.lib.res.model.tally.MoneyFen
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillInsertDto
import com.xiaojinzi.tally.lib.res.model.tally.TallyCategoryDto
import com.xiaojinzi.tally.module.base.support.AppRouterMainApi
import com.xiaojinzi.tally.module.base.support.AppRouterUserApi
import com.xiaojinzi.tally.module.base.support.AppServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.math.roundToLong

sealed class SettingIntent {

    data object Submit : SettingIntent()

    data class CheckUpdate(
        @UiContext val context: Context,
    ) : SettingIntent()

    data class Feedback(
        @UiContext val context: Context,
    ) : SettingIntent()

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
    private suspend fun checkUpdate(intent: SettingIntent.CheckUpdate) {
        AppRouterMainApi::class
            .routeApi()
            .toAppUpdateView(
                context = intent.context,
                isTip = true,
            )
    }

    @IntentProcess
    private suspend fun feedback(intent: SettingIntent.Feedback) {
        confirmDialogOrError(
            content = "需要进入 QQ 群进行反馈\n是否继续?".toStringItemDto(),
        )
        Router
            .with(
                context = intent.context,
            ).url(
                url = QQ_GROUP_LINK,
            ).forward()
    }


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
            // 鲨鱼记账导出的 CSV 为 GBK 编码
            val text = withContext(Dispatchers.IO) {
                intent.context.contentResolver
                    .openInputStream(intent.uri)
                    ?.use { it.readBytes().toString(charset = charset("GBK")) }
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

            val dateFormat = SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA)
            val billInsertList = mutableListOf<TallyBillInsertDto>()
            var skipCount = 0

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
                    val matchedCategory = categoryName?.let {
                        matchCategory(
                            name = it,
                            candidates = if (isSpending) spendingCategories else incomeCategories,
                        )
                    }

                    billInsertList.add(
                        element = TallyBillInsertDto(
                            userId = currentUserInfo.id,
                            bookId = currentBookInfo.id,
                            type = TallyBillDto.Type.NORMAL.value,
                            time = time,
                            categoryId = matchedCategory?.id,
                            amount = MoneyFen(
                                value = if (isSpending) -absFen else absFen,
                            ),
                            note = note,
                        )
                    )
                }

            if (billInsertList.isEmpty()) {
                tip(content = "没有可导入的有效数据".toStringItemDto())
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
                if (skipCount > 0) {
                    append(", 跳过 ")
                    append(skipCount)
                    append(" 条")
                }
            }
            tip(content = tipContent.toStringItemDto())
        } finally {
            hideLoading()
        }
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