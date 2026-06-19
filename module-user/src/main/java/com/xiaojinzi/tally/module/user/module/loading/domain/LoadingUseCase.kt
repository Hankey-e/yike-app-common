package com.xiaojinzi.tally.module.user.module.loading.domain

import android.content.Context
import androidx.annotation.UiContext
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCase
import com.xiaojinzi.reactive.template.domain.BusinessMVIUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.activity_stack.ActivityStack
import com.xiaojinzi.support.annotation.ViewModelLayer
import com.xiaojinzi.support.ktx.tryFinishActivity
import com.xiaojinzi.tally.lib.res.ui.APP_ACTIVITY_FLAG_MAIN
import com.xiaojinzi.tally.module.base.support.AppRouterMainApi
import com.xiaojinzi.tally.module.base.support.AppRouterUserApi
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.base.support.finishAppAllTask
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

sealed class LoadingIntent {

    data class GO(
        @UiContext val context: Context,
    ) : LoadingIntent()

}

@ViewModelLayer
interface LoadingUseCase : BusinessMVIUseCase {
    // TODO
}

@ViewModelLayer
class LoadingUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
) : BusinessMVIUseCaseImpl(
    commonUseCase = commonUseCase,
), LoadingUseCase {

    private suspend fun goNext(
        @UiContext context: Context,
    ) {
        var latestUserId = AppServices
            .userSpi
            .latestUserIdStateOb
            .firstOrNull()
        // 开源离线版: 没有本地用户时, 静默完成本地登录(等价于登录页原本的自动登录),
        // 复用同一套 loginByCheckCode -> afterLogin(建立本地用户 + 初始化数据库 + 种子数据),
        // 从而彻底去掉登录页, 用户进入即用。
        if (latestUserId.isNullOrBlank()) {
            kotlin.runCatching {
                AppServices
                    .userSpi
                    .loginByCheckCode(
                        phoneNumber = "18888888888",
                        checkCode = "123456",
                    )
            }
            latestUserId = AppServices
                .userSpi
                .latestUserIdStateOb
                .firstOrNull()
        }
        // 如果主界面存在, 就关闭当前界面, 否则就启动一个
        val isMainViewExist = ActivityStack.any {
            it.hasFlag(
                flag = APP_ACTIVITY_FLAG_MAIN,
            )
        }
        if (isMainViewExist) {
            // 不然太快了, 会闪
            delay(800)
            context.tryFinishActivity()
        } else {
            // 等数据库初始化完成再进主界面(最多等 5 秒), 避免主界面读数据库时崩溃。
            // 注意: 不能访问 tallyDataSourceSpi —— 它的构造函数就会读数据库, 未初始化时会直接抛
            // 「数据库未初始化」。这里改用初始化状态 SPI(它的构造不碰数据库), 等它发出 true。
            withTimeoutOrNull(timeMillis = 5_000) {
                AppServices
                    .tallyDataSourceInitSpi
                    .isInitStateOb
                    .filter { it }
                    .first()
            }

            // 去主界面
            AppRouterMainApi::class
                .routeApi()
                .toMainView(
                    context = context,
                ) {
                    context.tryFinishActivity()
                }
        }
    }

    @IntentProcess
    private suspend fun go(
        intent: LoadingIntent.GO,
    ) {
        val forOpenSource = AppServices
            .appInfoSpi
            .forOpenSource
        val isAgreedPrivacyAgreement =
            AppServices.appConfigSpi.isAgreedPrivacyAgreementStateOb.first()
        try {
            if (!forOpenSource) {
                if (!isAgreedPrivacyAgreement) {
                    AppRouterUserApi::class
                        .routeApi()
                        .privacyAgreementBySuspend(
                            context = intent.context,
                        )
                    // 进行本地写入
                    AppServices.appConfigSpi.isAgreedPrivacyAgreementStateOb.emit(
                        value = true
                    )
                }
            } else {
                // 开源离线版: 无隐私网关, 直接置为已同意,
                // 避免 BaseApplication 启动初始化(等待同意隐私协议)被永久阻塞。
                AppServices.appConfigSpi.isAgreedPrivacyAgreementStateOb.emit(
                    value = true
                )
            }
            goNext(
                context = intent.context,
            )
        } catch (e: Exception) {
            finishAppAllTask()
        }
    }

    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}