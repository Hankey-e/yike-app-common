package com.xiaojinzi.tally.module.core.module.bill_list.domain

import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCase
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewModelLayer
import com.xiaojinzi.support.ktx.MutableSharedStateFlow
import com.xiaojinzi.support.ktx.sharedStateIn
import com.xiaojinzi.tally.lib.res.model.tally.TallyTable
import com.xiaojinzi.tally.module.base.module.common_bill_list.domain.CommonBillQueryConditionUseCase
import com.xiaojinzi.tally.module.base.module.common_bill_list.domain.CommonBillQueryConditionUseCaseImpl
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListNormalItemVo
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.toCommonBillListItemClipType
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.toCommonBillListNormalItemVo
import com.xiaojinzi.tally.module.base.support.AppServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlin.math.absoluteValue

/**
 * 账单列表的排序方式
 */
enum class BillSortType {
    // 默认: 按天分组(时间倒序)
    Default,

    // 金额从高到低
    AmountDesc,

    // 金额从低到高
    AmountAsc,
}

sealed class BillListIntent {

    data object Submit : BillListIntent()

    // 循环切换排序: 默认 -> 金额高到低 -> 金额低到高 -> 默认
    data object CycleSort : BillListIntent()

}

@ViewModelLayer
interface BillListUseCase : BusinessMVIUseCase {

    val billQueryConditionUseCase: CommonBillQueryConditionUseCase

    /**
     * 当前排序方式
     */
    @StateHotObservable
    val sortTypeStateOb: MutableSharedStateFlow<BillSortType>

    /**
     * 金额排序模式下的平铺列表(不按天分组)。默认模式下为空(由通用列表负责渲染)。
     */
    @StateHotObservable
    val sortedBillVoListStateOb: Flow<List<CommonBillListNormalItemVo>>

}

@ViewModelLayer
class BillListUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
    override val billQueryConditionUseCase: CommonBillQueryConditionUseCase = CommonBillQueryConditionUseCaseImpl(),
) : BusinessMVIUseCaseImpl(
    commonUseCase = commonUseCase,
), BillListUseCase {

    override val sortTypeStateOb = MutableSharedStateFlow(
        initValue = BillSortType.Default,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    override val sortedBillVoListStateOb = combine(
        billQueryConditionUseCase.queryConditionStateOb,
        sortTypeStateOb,
    ) { condition, sortType ->
        condition to sortType
    }.flatMapLatest { (condition, sortType) ->
        if (condition == null || sortType == BillSortType.Default) {
            flowOf(value = emptyList())
        } else {
            val tallyDataSourceSpi = AppServices.tallyDataSourceSpi
            tallyDataSourceSpi
                .subscribeDataBaseTableChangedOb(
                    TallyTable.Bill,
                    TallyTable.BillLabel,
                    TallyTable.Account,
                    TallyTable.Category,
                    emitOneWhileSubscribe = true,
                )
                .map {
                    // 金额排序需要全量数据: 一次查回再按金额绝对值排序
                    val list = tallyDataSourceSpi.getBillDetailListByCondition(
                        queryCondition = condition,
                    )
                    val sortedList = when (sortType) {
                        BillSortType.AmountDesc -> list.sortedByDescending { it.core.amount.value.absoluteValue }
                        BillSortType.AmountAsc -> list.sortedBy { it.core.amount.value.absoluteValue }
                        else -> list
                    }
                    sortedList.mapIndexed { index, detail ->
                        detail.toCommonBillListNormalItemVo(
                            clipType = index.toCommonBillListItemClipType(size = sortedList.size),
                        )
                    }
                }
        }
    }.sharedStateIn(
        scope = scope,
        initValue = emptyList(),
    )

    @BusinessMVIUseCase.AutoLoading
    @IntentProcess
    private suspend fun submit(intent: BillListIntent.Submit) {
        // TODO
    }

    @IntentProcess
    private suspend fun cycleSort(intent: BillListIntent.CycleSort) {
        val next = when (sortTypeStateOb.first()) {
            BillSortType.Default -> BillSortType.AmountDesc
            BillSortType.AmountDesc -> BillSortType.AmountAsc
            BillSortType.AmountAsc -> BillSortType.Default
        }
        sortTypeStateOb.emit(value = next)
    }

    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}
