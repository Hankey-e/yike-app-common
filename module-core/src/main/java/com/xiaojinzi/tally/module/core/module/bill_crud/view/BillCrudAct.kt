package com.xiaojinzi.tally.module.core.module.bill_crud.view

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import com.xiaojinzi.component.anno.AttrValueAutowiredAnno
import com.xiaojinzi.component.anno.RouterAnno
import com.xiaojinzi.support.activity_stack.ActivityFlag
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.support.compose.StateBar
import com.xiaojinzi.support.ktx.initOnceUseViewModel
import com.xiaojinzi.tally.lib.res.model.tally.TallyBillDto
import com.xiaojinzi.tally.lib.res.ui.APP_ACTIVITY_FLAG_SYNC_SKIP
import com.xiaojinzi.tally.module.base.support.AppRouterConfig
import com.xiaojinzi.tally.module.base.theme.AppTheme
import com.xiaojinzi.tally.module.base.view.BaseBusinessAct
import com.xiaojinzi.tally.module.core.module.bill_crud.domain.BillCrudIntent
import kotlinx.coroutines.InternalCoroutinesApi

@RouterAnno(
    hostAndPath = AppRouterConfig.CORE_BILL_CURD,
)
@ActivityFlag(
    value = [
        APP_ACTIVITY_FLAG_SYNC_SKIP,
    ],
)
@ViewLayer
class BillCrudAct : BaseBusinessAct<BillCrudViewModel>() {

    // 编辑的时候用
    @AttrValueAutowiredAnno("billId")
    var billId: String? = null

    // 如果 billId 为 null, refundBillId 不为 null, 则表示生成一个退款单
    // billId 如果有值, 则表示一定是编辑账单, 其他参数基本上都是无效的, 包括 refundBillId
    @AttrValueAutowiredAnno("associatedRefundBillId")
    var associatedRefundBillId: String? = null

    @AttrValueAutowiredAnno("billType")
    var initBillType: TallyBillDto.Type = TallyBillDto.Type.NORMAL

    @AttrValueAutowiredAnno("initBookId")
    var initBookId: String? = null

    @AttrValueAutowiredAnno("initCategoryId")
    var initCategoryId: String? = null

    @AttrValueAutowiredAnno("initAccountId")
    var initAccountId: String? = null

    @AttrValueAutowiredAnno("initTransferAccountId")
    var initTransferAccountId: String? = null

    @AttrValueAutowiredAnno("initTransferTargetAccountId")
    var initTransferTargetAccountId: String? = null

    @AttrValueAutowiredAnno("initLabelIdList")
    var initLabelIdList: ArrayList<String>? = null

    @AttrValueAutowiredAnno("initImageUrlList")
    var initImageUrlList: ArrayList<String>? = null

    @AttrValueAutowiredAnno("initTime")
    var initTime: Long? = null

    @AttrValueAutowiredAnno("initAmount")
    var initAmount: Long? = null

    override fun getViewModelClass(): Class<BillCrudViewModel> {
        return BillCrudViewModel::class.java
    }

    @OptIn(
        InternalCoroutinesApi::class,
        ExperimentalMaterial3Api::class,
        ExperimentalAnimationApi::class,
        ExperimentalFoundationApi::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 记账页从底部浮出 (打开下滑入, 关闭下滑出)
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN,
                com.xiaojinzi.tally.lib.res.R.anim.res_slide_in_up,
                com.xiaojinzi.tally.lib.res.R.anim.res_hold,
            )
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_CLOSE,
                com.xiaojinzi.tally.lib.res.R.anim.res_hold,
                com.xiaojinzi.tally.lib.res.R.anim.res_slide_out_down,
            )
        } else {
            @Suppress("DEPRECATION")
            overridePendingTransition(
                com.xiaojinzi.tally.lib.res.R.anim.res_slide_in_up,
                com.xiaojinzi.tally.lib.res.R.anim.res_hold,
            )
        }

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        initOnceUseViewModel {
            requiredViewModel().addIntent(
                intent = BillCrudIntent.ParameterInit(
                    billId = billId,
                    associatedRefundBillId = associatedRefundBillId,
                    bookId = initBookId,
                    billType = initBillType,
                    categoryId = initCategoryId,
                    accountId = initAccountId,
                    transferAccountId = initTransferAccountId,
                    transferTargetAccountId = initTransferTargetAccountId,
                    labelIdSet = initLabelIdList?.toSet() ?: emptySet(),
                    imageUrlList = initImageUrlList ?: emptyList(),
                    time = initTime,
                    amount = initAmount,
                )
            )
        }

        setContent {
            AppTheme {
                StateBar {
                    BillCrudViewWrap()
                }
            }
        }

    }

    @Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
    override fun finish() {
        super.finish()
        // 关闭时向底部滑出 (API 34+ 已由 overrideActivityTransition 处理)
        if (Build.VERSION.SDK_INT < 34) {
            overridePendingTransition(
                com.xiaojinzi.tally.lib.res.R.anim.res_hold,
                com.xiaojinzi.tally.lib.res.R.anim.res_slide_out_down,
            )
        }
    }

}