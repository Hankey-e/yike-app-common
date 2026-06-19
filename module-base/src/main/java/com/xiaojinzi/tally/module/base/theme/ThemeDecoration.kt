package com.xiaojinzi.tally.module.base.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_AMETHYST
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CELADON
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CHAMPAGNE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CHINA_RED
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CONCRETE_GRAY
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_FOLLOW_SYSTEM
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_GLASS_TOWER
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_BAUHAUS
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_INK_SLATE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_KLEIN_BLUE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_OLIVE_GREEN
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_SANDSTONE_BEIGE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_SCHENBRUNN_YELLOW
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_TITIAN_RED

/**
 * 主题装饰: 承载"配色之外"的主题化资源与 UI 定制项.
 *
 * 插画统一由 [ThemeIllustrations] 中的 Composable 实时绘制, 颜色取自当前主题的 ColorScheme,
 * 因此**每个主题都自动拥有一整套自己配色的插图**, 且随深浅色变化; 通过 [illustrationStyle]
 * 为不同主题分配不同构图(远山/暖浪/几何), 实现"千题千面". 新增主题无需再单独画图.
 *
 * 没有配置富装饰的主题使用 [DefaultThemeDecoration], 各组件自动回退到原有默认样式, 完全向后兼容.
 */
data class ThemeDecoration(
    /**
     * 是否为富主题. 为 false 时各组件保持原有默认 UI.
     */
    val isRich: Boolean = false,
    /**
     * 是否在首页头部显示问候语 "Hi, xxx".
     */
    val showGreeting: Boolean = false,
    /**
     * 插画风格. null 表示不显示任何主题插画(回退默认).
     */
    val illustrationStyle: ThemeIllustrationStyle? = null,
    /**
     * 账户页"净资产"卡片角落的水印纹样(单色, 会被 tint 成 onSecondary, 因此对所有主题通用).
     * null 表示不显示.
     */
    @DrawableRes val accountCardMotifRsd: Int? = null,
)

/**
 * 默认装饰: 不启用任何富主题特性, 各组件维持现有默认样式.
 */
val DefaultThemeDecoration = ThemeDecoration()

/**
 * 当前生效的主题装饰. 由 AppTheme 根据主题名下发.
 */
val LocalThemeDecoration = staticCompositionLocalOf { DefaultThemeDecoration }

private val cardMotifRsd: Int
    get() = com.xiaojinzi.tally.lib.res.R.drawable.res_theme_card_motif

private fun richDecoration(style: ThemeIllustrationStyle) = ThemeDecoration(
    isRich = true,
    showGreeting = true,
    illustrationStyle = style,
    accountCardMotifRsd = cardMotifRsd,
)

/**
 * 主题名 -> 主题装饰 的映射. 每个主题分配一种插画构图, 配色自动取自该主题.
 */
fun themeDecorationOf(themeName: String?): ThemeDecoration = when (themeName) {
    // 清雅 / 冷调 -> 远山薄雾
    THEME_NAME_CELADON -> richDecoration(ThemeIllustrationStyle.MOUNTAIN)
    THEME_NAME_KLEIN_BLUE -> richDecoration(ThemeIllustrationStyle.MOUNTAIN)
    THEME_NAME_OLIVE_GREEN -> richDecoration(ThemeIllustrationStyle.MOUNTAIN)
    THEME_NAME_AMETHYST -> richDecoration(ThemeIllustrationStyle.MOUNTAIN)
    THEME_NAME_FOLLOW_SYSTEM -> richDecoration(ThemeIllustrationStyle.MOUNTAIN)

    // 暖调 -> 暖阳层浪
    THEME_NAME_CHINA_RED -> richDecoration(ThemeIllustrationStyle.WAVE)
    THEME_NAME_TITIAN_RED -> richDecoration(ThemeIllustrationStyle.WAVE)
    THEME_NAME_SCHENBRUNN_YELLOW -> richDecoration(ThemeIllustrationStyle.WAVE)
    THEME_NAME_CHAMPAGNE -> richDecoration(ThemeIllustrationStyle.WAVE)
    THEME_NAME_SANDSTONE_BEIGE -> richDecoration(ThemeIllustrationStyle.WAVE)

    // 建筑 / 中性 -> 几何天际线
    THEME_NAME_CONCRETE_GRAY -> richDecoration(ThemeIllustrationStyle.GEOMETRIC)
    THEME_NAME_INK_SLATE -> richDecoration(ThemeIllustrationStyle.GEOMETRIC)
    THEME_NAME_GLASS_TOWER -> richDecoration(ThemeIllustrationStyle.GEOMETRIC)
    THEME_NAME_BAUHAUS -> richDecoration(ThemeIllustrationStyle.GEOMETRIC)

    else -> richDecoration(ThemeIllustrationStyle.MOUNTAIN)
}
