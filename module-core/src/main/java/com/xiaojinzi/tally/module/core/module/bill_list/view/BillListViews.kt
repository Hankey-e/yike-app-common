package com.xiaojinzi.tally.module.core.module.bill_list.view

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_SMALL
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListNormalItemView
import com.xiaojinzi.tally.module.base.module.common_bill_list.view.CommonBillListView
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.core.module.bill_list.domain.BillListIntent
import com.xiaojinzi.tally.module.core.module.bill_list.domain.BillSortType
import kotlinx.coroutines.InternalCoroutinesApi

@Composable
private fun BillSortBar(
    sortType: BillSortType,
    onClick: () -> Unit,
) {
    val label = when (sortType) {
        BillSortType.Default -> "默认"
        BillSortType.AmountDesc -> "金额：高→低"
        BillSortType.AmountAsc -> "金额：低→高"
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_SMALL.dp)
            .nothing(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = "排序：$label",
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary,
            ),
        )
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
private fun BillListView(
    needInit: Boolean? = null,
) {
    val context = LocalContext.current
    BusinessContentView<BillListViewModel>(
        needInit = needInit,
    ) { vm ->
        val sortType by vm.sortTypeStateOb.collectAsState(initial = BillSortType.Default)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .nothing(),
        ) {
            BillSortBar(sortType = sortType) {
                vm.addIntent(intent = BillListIntent.CycleSort)
            }
            if (sortType == BillSortType.Default) {
                // 默认: 复用通用的按天分组列表
                CommonBillListView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f)
                        .nothing(),
                    commonBillListViewUseCase = vm.commonBillListViewUseCase,
                )
            } else {
                // 金额排序: 平铺展示, 按金额绝对值排序
                val sortedList by vm.sortedBillVoListStateOb.collectAsState(initial = emptyList())
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(weight = 1f)
                        .nothing(),
                ) {
                    items(
                        items = sortedList,
                        key = { it.billId },
                    ) { vo ->
                        CommonBillListNormalItemView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    AppRouterCoreApi::class
                                        .routeApi()
                                        .toBillDetailView(
                                            context = context,
                                            billId = vo.billId,
                                        )
                                }
                                .background(color = MaterialTheme.colorScheme.surface)
                                .padding(
                                    horizontal = APP_PADDING_NORMAL.dp,
                                    vertical = APP_PADDING_NORMAL.dp,
                                )
                                .nothing(),
                            normalItem = vo,
                            showBookInfo = false,
                        )
                    }
                }
            }
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun BillListViewWrap(title: StringItemDto? = null) {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = title,
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            BillListView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun BillListViewPreview() {
    BillListView(
        needInit = false,
    )
}
