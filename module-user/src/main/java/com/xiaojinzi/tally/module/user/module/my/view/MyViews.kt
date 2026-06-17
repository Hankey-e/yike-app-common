package com.xiaojinzi.tally.module.user.module.my.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.template.view.BusinessContentView
import com.xiaojinzi.support.bean.StringItemDto
import com.xiaojinzi.support.compose.util.circleClip
import com.xiaojinzi.support.compose.util.clickableNoRipple
import com.xiaojinzi.support.compose.util.contentWithComposable
import com.xiaojinzi.support.ktx.commonTimeFormat2
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.shake
import com.xiaojinzi.support.ktx.toStringItemDto
import com.xiaojinzi.tally.lib.res.R
import com.xiaojinzi.tally.lib.res.model.support.LocalImageItemDto
import com.xiaojinzi.tally.lib.res.model.support.rememberPainter
import com.xiaojinzi.tally.lib.res.model.support.toLocalImageItemDto
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_LARGE
import com.xiaojinzi.tally.lib.res.ui.APP_PADDING_NORMAL
import com.xiaojinzi.tally.lib.res.ui.AppBackgroundColor
import com.xiaojinzi.tally.lib.res.ui.AppHeightSpace
import com.xiaojinzi.tally.lib.res.ui.AppWidthSpace
import com.xiaojinzi.tally.module.base.support.AppRouterCoreApi
import com.xiaojinzi.tally.module.base.support.AppRouterMainApi
import com.xiaojinzi.tally.module.base.support.AppRouterSystemApi
import com.xiaojinzi.tally.module.base.support.AppRouterUserApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.support.DevelopHelper
import com.xiaojinzi.tally.module.base.view.compose.AppbarNormalM3
import com.xiaojinzi.tally.module.user.module.my.domain.MyIntent
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf

private val Shape1 = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val temp = 0.85f
        return Outline.Generic(
            path = Path().apply {
                this.reset()
                this.lineTo(
                    x = 0f, y = size.height * temp,
                )
                this.quadraticBezierTo(
                    x1 = size.width / 2f, y1 = size.height,
                    x2 = size.width, y2 = size.height * temp,
                )
                this.lineTo(
                    x = size.width, y = 0f,
                )
                this.close()
            },
        )
    }
}

@Composable
fun MyItemActionView2(
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(
            color = MaterialTheme.colorScheme.surface,
        )
        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
        .nothing(),
    image: LocalImageItemDto,
    title: StringItemDto,
    onclick: () -> Unit = {},
) {

    Row(
        modifier = Modifier
            .clickable {
                onclick.invoke()
            }
            .then(other = modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(size = 16.dp)
                .nothing(),
            painter = image.rememberPainter(),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
        AppWidthSpace()
        Text(
            text = title.contentWithComposable(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        Icon(
            modifier = Modifier
                .size(size = 16.dp)
                .nothing(),
            painter = rememberVectorPainter(image = Icons.Rounded.KeyboardArrowRight),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }

}

@Composable
fun MyItemActionView3(
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(
            color = MaterialTheme.colorScheme.surface,
        )
        .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
        .nothing(),
    image: LocalImageItemDto,
    title: StringItemDto,
    value: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) {

    Row(
        modifier = Modifier
            .then(other = modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(size = 16.dp)
                .nothing(),
            painter = image.rememberPainter(),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
        AppWidthSpace()
        Text(
            text = title.contentWithComposable(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
        Switch(
            modifier = Modifier
                .scale(scale = 0.6f)
                .nothing(),
            checked = value,
            onCheckedChange = onCheckedChange,
        )
    }

}

@Composable
fun MyItemActionView1(
    @SuppressLint("ModifierParameter")
    modifier: Modifier = Modifier
        .wrapContentSize()
        .nothing(),
    image: LocalImageItemDto,
    title: StringItemDto,
    onclick: () -> Unit = {},
) {

    Column(
        modifier = Modifier
            .clickableNoRipple {
                onclick.invoke()
            }
            .then(other = modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .size(size = 18.dp)
                .nothing(),
            painter = image.rememberPainter(),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(
            modifier = Modifier
                .height(height = 4.dp)
                .nothing()
        )
        Text(
            text = title.contentWithComposable(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            ),
            textAlign = TextAlign.Start,
        )
    }

}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MyView(
    needInit: Boolean? = false,
) {
    val context = LocalContext.current
    val selfUserInfo by AppServices.userSpi.userInfoStateOb.collectAsState(initial = null)
    val vipInfo by AppServices.userSpi.vipInfoStateOb.collectAsState(initial = null)
    val isVip by AppServices.userSpi.isVipStateOb.collectAsState(initial = false)
    BusinessContentView<MyViewModel>(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AppBackgroundColor,
            )
            .nothing(),
        needInit = needInit,
    ) { vm ->
        val totalDay by vm.totalDayStateOb.collectAsState(initial = null)
        val totalBillCount by vm.totalBillCountStateOb.collectAsState(initial = null)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 1.5f)
                .clip(
                    shape = Shape1,
                )
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer.copy(
                                alpha = 0.6f,
                            ),
                            MaterialTheme.colorScheme.primaryContainer,
                        ),
                    )
                )
                .nothing(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = rememberScrollState(),
                )
                .statusBarsPadding()
                .nothing(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(
                modifier = Modifier
                    .height(height = 24.dp)
                    .nothing()
            )

            Row(
                modifier = Modifier
                    .padding(horizontal = (APP_PADDING_LARGE * 2).dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickableNoRipple {
                        if (selfUserInfo == null) {
                            AppRouterUserApi::class
                                .routeApi()
                                .toLoginView(
                                    context = context,
                                )
                        } else {
                            AppRouterUserApi::class
                                .routeApi()
                                .toUserInfoView(
                                    context = context,
                                )
                        }
                    }
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Image(
                    modifier = Modifier
                        .size(size = 48.dp)
                        .circleClip()
                        .nothing(),
                    painter = rememberAsyncImagePainter(model = AppServices.appInfoSpi.appLauncherIconRsd),
                    contentDescription = null,
                )
                AppWidthSpace()
                Text(
                    text = selfUserInfo?.name ?: "登录已过期",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    textAlign = TextAlign.Start,
                )
            }

            Spacer(
                modifier = Modifier
                    .height(height = 20.dp)
                    .nothing()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .nothing(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .nothing(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = totalDay?.toString() ?: "---",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Spacer(
                        modifier = Modifier
                            .height(height = 4.dp)
                            .nothing()
                    )
                    Text(
                        text = "记账总天数",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .nothing(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = totalBillCount?.toString() ?: "---",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        textAlign = TextAlign.Start,
                    )
                    Spacer(
                        modifier = Modifier
                            .height(height = 4.dp)
                            .nothing()
                    )
                    Text(
                        text = "记账总笔数",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                        textAlign = TextAlign.Start,
                    )
                }
            }

            Spacer(
                modifier = Modifier
                    .height(height = 10.dp)
                    .nothing()
            )

            // VIP 已全员解锁, 升级/会员卡片不再展示
            if (!isVip) {
            Row(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .shadow(
                        elevation = 1.dp,
                        shape = MaterialTheme.shapes.small,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .clickable {
                        AppRouterUserApi::class
                            .routeApi()
                            .toVipBuyView(
                                context = context,
                            )
                    }
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Icon(
                    modifier = Modifier
                        .size(size = 16.dp)
                        .nothing(),
                    painter = painterResource(id = R.drawable.res_vip1),
                    contentDescription = null,
                    tint = if (isVip) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    },
                )

                AppWidthSpace()

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .nothing(),
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .nothing(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = if (isVip) {
                                "尊贵的 Vip"
                            } else {
                                "升级为 VIP"
                            },
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = if (isVip) {
                                    MaterialTheme.colorScheme.primary
                                } else {
                                    MaterialTheme.colorScheme.onSurface
                                },
                            ),
                            textAlign = TextAlign.Start,
                        )
                        Row(
                            modifier = Modifier
                                .circleClip()
                                .clickable {
                                    vm.addIntent(
                                        intent = MyIntent.VipRefresh,
                                    )
                                }
                                .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 4.dp)
                                .wrapContentSize()
                                .nothing(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = Modifier
                                    .nothing(),
                                text = "刷新状态",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                ),
                                textAlign = TextAlign.Start,
                            )
                            Icon(
                                modifier = Modifier
                                    .scale(scale = 0.6f)
                                    .size(size = 20.dp)
                                    .nothing(),
                                painter = painterResource(
                                    id = R.drawable.res_refresh1,
                                ),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .height(height = 2.dp)
                            .nothing()
                    )

                    Text(
                        text = if (isVip) {
                            "过期时间：${vipInfo?.expiredTime?.commonTimeFormat2().orEmpty()}"
                        } else {
                            "畅享更多高级功能"
                        },
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.8f,
                            ),
                        ),
                        textAlign = TextAlign.Start,
                    )

                }

                Spacer(modifier = Modifier.weight(weight = 1f, fill = true))

                Icon(
                    modifier = Modifier
                        .size(size = 18.dp)
                        .nothing(),
                    painter = rememberVectorPainter(
                        image = Icons.Outlined.KeyboardArrowRight,
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )

            }

            } // 结束 if (!isVip), VIP 卡片不再展示

            AppHeightSpace()

            Row(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .shadow(
                        elevation = 1.dp,
                        shape = MaterialTheme.shapes.small,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = APP_PADDING_NORMAL.dp)
                    .nothing(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                MyItemActionView1(
                    image = R.drawable.res_book1.toLocalImageItemDto(),
                    title = "账本管理".toStringItemDto(),
                ) {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toBookInfoView(
                            context = context,
                        )
                }
                MyItemActionView1(
                    image = R.drawable.res_money1.toLocalImageItemDto(),
                    title = "资产管理".toStringItemDto(),
                ) {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toAccountInfoView(
                            context = context,
                        )
                }
                MyItemActionView1(
                    image = R.drawable.res_category_manage1.toLocalImageItemDto(),
                    title = "类别管理".toStringItemDto(),
                ) {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toCategoryInfoView(
                            context = context,
                        )
                }
                MyItemActionView1(
                    image = R.drawable.res_label1.toLocalImageItemDto(),
                    title = "标签管理".toStringItemDto(),
                ) {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toLabelInfoView(
                            context = context,
                        )
                }
                MyItemActionView1(
                    image = R.drawable.res_image1.toLocalImageItemDto(),
                    title = "账单相册".toStringItemDto(),
                ) {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toBillAlbumView(
                            context = context,
                        )
                }
            }

            AppHeightSpace()

            Column(
                modifier = Modifier
                    .padding(horizontal = APP_PADDING_NORMAL.dp, vertical = 0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(
                        shape = MaterialTheme.shapes.small,
                    )
                    .shadow(
                        elevation = 1.dp,
                        shape = MaterialTheme.shapes.small,
                    )
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .nothing(),
            ) {
                // 数据同步功能已移除(本软件无需上网)

                val isVibrate by AppServices.appConfigSpi.isVibrateStateOb.collectAsState(initial = true)
                MyItemActionView3(
                    image = R.drawable.res_shake1.toLocalImageItemDto(),
                    title = "数字键盘振动反馈".toStringItemDto(),
                    value = isVibrate,
                ) { b ->
                    if (b) {

                        shake()
                    }
                    AppServices.appConfigSpi.switchVibrate(
                        isVibrate = b,
                    )
                }
                MyItemActionView2(
                    image = R.drawable.res_calendar1.toLocalImageItemDto(),
                    title = "周期记账".toStringItemDto(),
                ) {
                    AppRouterCoreApi::class
                        .routeApi()
                        .toBillCycleView(
                            context = context,
                        )
                }
                MyItemActionView2(
                    image = R.drawable.res_theme1.toLocalImageItemDto(),
                    title = "主题样式".toStringItemDto(),
                ) {
                    AppRouterMainApi::class
                        .routeApi()
                        .toThemeSelectView(
                            context = context,
                        )
                }
                // 仅个人使用, 已移除「分享给好友」「给一刻记账好评」等上架相关入口
                MyItemActionView2(
                    image = R.drawable.res_setting1.toLocalImageItemDto(),
                    title = "设置".toStringItemDto(),
                ) {
                    AppRouterMainApi::class
                        .routeApi()
                        .toSettingView(
                            context = context,
                        )
                }
            }

            AppHeightSpace()

        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MyViewWrap() {
    Scaffold(
        topBar = {
            AppbarNormalM3(
                title = "我的".toStringItemDto(),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .nothing(),
        ) {
            MyView()
        }
    }
}

@InternalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
private fun MyViewPreview() {
    MyView(
        needInit = false,
    )
}