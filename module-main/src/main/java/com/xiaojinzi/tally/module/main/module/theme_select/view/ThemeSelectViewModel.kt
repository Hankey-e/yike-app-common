package com.xiaojinzi.tally.module.main.module.theme_select.view

import android.os.Build
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.StateHotObservable
import com.xiaojinzi.support.annotation.ViewLayer
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CHINA_RED
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_FOLLOW_SYSTEM
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_KLEIN_BLUE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_OLIVE_GREEN
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_SCHENBRUNN_YELLOW
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_TITIAN_RED
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CONCRETE_GRAY
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_SANDSTONE_BEIGE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_INK_SLATE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_AMETHYST
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CELADON
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_CHAMPAGNE
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_GLASS_TOWER
import com.xiaojinzi.tally.lib.res.ui.THEME_NAME_BAUHAUS
import com.xiaojinzi.tally.lib.res.ui.theme.concrete.*
import com.xiaojinzi.tally.lib.res.ui.theme.sandstone.*
import com.xiaojinzi.tally.lib.res.ui.theme.inkslate.*
import com.xiaojinzi.tally.lib.res.ui.theme.amethyst.*
import com.xiaojinzi.tally.lib.res.ui.theme.celadon.*
import com.xiaojinzi.tally.lib.res.ui.theme.champagne.*
import com.xiaojinzi.tally.lib.res.ui.theme.glasstower.*
import com.xiaojinzi.tally.lib.res.ui.theme.bauhaus.*
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_dark_primary
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_dark_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_dark_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_dark_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_dark_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_dark_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_light_primary
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_light_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_light_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_light_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_light_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.five.TitianRed_md_theme_light_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_dark_primary
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_dark_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_dark_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_dark_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_dark_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_dark_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_light_primary
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_light_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_light_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_light_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_light_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.four.Schenbrunn_Yellowmd_md_theme_light_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_dark_primary
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_dark_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_dark_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_dark_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_dark_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_dark_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_light_primary
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_light_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_light_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_light_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_light_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.one.ChinaRed_md_theme_light_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_dark_primary
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_dark_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_dark_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_dark_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_dark_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_dark_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_light_primary
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_light_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_light_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_light_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_light_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.three.OliveGreen_md_theme_light_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_dark_primary
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_dark_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_dark_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_dark_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_dark_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_dark_tertiaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_light_primary
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_light_primaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_light_secondary
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_light_secondaryContainer
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_light_tertiary
import com.xiaojinzi.tally.lib.res.ui.theme.two.KleinBlue_md_theme_light_tertiaryContainer
import com.xiaojinzi.tally.module.base.support.AppServices
import com.xiaojinzi.tally.module.main.module.theme_select.domain.ThemeSelectUseCase
import com.xiaojinzi.tally.module.main.module.theme_select.domain.ThemeSelectUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

@ViewLayer
class ThemeSelectViewModel(
    private val useCase: ThemeSelectUseCase = ThemeSelectUseCaseImpl(),
) : BaseViewModel(),
    ThemeSelectUseCase by useCase {

    @OptIn(ExperimentalCoroutinesApi::class)
    @StateHotObservable
    val themeListStateObVo: Flow<List<ThemeSelectItemVo>> = AppServices
        .appInfoSpi
        .themeNameState
        .flatMapLatest { themeName ->
            flowOf(
                arrayListOf(
                    ThemeSystemSelectItemVo(
                        name = "系统配色",
                        themeName = THEME_NAME_FOLLOW_SYSTEM,
                        isSelected = themeName == THEME_NAME_FOLLOW_SYSTEM,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "中国红",
                        themeName = THEME_NAME_CHINA_RED,
                        isSelected = themeName == THEME_NAME_CHINA_RED,
                        isNeedVip = false,
                        lightPrimary = ChinaRed_md_theme_light_primary,
                        lightPrimaryContainer = ChinaRed_md_theme_light_primaryContainer,
                        darkPrimary = ChinaRed_md_theme_dark_primary,
                        darkPrimaryContainer = ChinaRed_md_theme_dark_primaryContainer,
                        lightSecondary = ChinaRed_md_theme_light_secondary,
                        lightSecondaryContainer = ChinaRed_md_theme_light_secondaryContainer,
                        darkSecondary = ChinaRed_md_theme_dark_secondary,
                        darkSecondaryContainer = ChinaRed_md_theme_dark_secondaryContainer,
                        lightTertiary = ChinaRed_md_theme_light_tertiary,
                        lightTertiaryContainer = ChinaRed_md_theme_light_tertiaryContainer,
                        darkTertiary = ChinaRed_md_theme_dark_tertiary,
                        darkTertiaryContainer = ChinaRed_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "克莱因蓝",
                        themeName = THEME_NAME_KLEIN_BLUE,
                        isSelected = themeName == THEME_NAME_KLEIN_BLUE,
                        lightPrimary = KleinBlue_md_theme_light_primary,
                        lightPrimaryContainer = KleinBlue_md_theme_light_primaryContainer,
                        darkPrimary = KleinBlue_md_theme_dark_primary,
                        darkPrimaryContainer = KleinBlue_md_theme_dark_primaryContainer,
                        lightSecondary = KleinBlue_md_theme_light_secondary,
                        lightSecondaryContainer = KleinBlue_md_theme_light_secondaryContainer,
                        darkSecondary = KleinBlue_md_theme_dark_secondary,
                        darkSecondaryContainer = KleinBlue_md_theme_dark_secondaryContainer,
                        lightTertiary = KleinBlue_md_theme_light_tertiary,
                        lightTertiaryContainer = KleinBlue_md_theme_light_tertiaryContainer,
                        darkTertiary = KleinBlue_md_theme_dark_tertiary,
                        darkTertiaryContainer = KleinBlue_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "橄榄绿",
                        themeName = THEME_NAME_OLIVE_GREEN,
                        isSelected = themeName.isEmpty() || themeName == THEME_NAME_OLIVE_GREEN,
                        lightPrimary = OliveGreen_md_theme_light_primary,
                        lightPrimaryContainer = OliveGreen_md_theme_light_primaryContainer,
                        darkPrimary = OliveGreen_md_theme_dark_primary,
                        darkPrimaryContainer = OliveGreen_md_theme_dark_primaryContainer,
                        lightSecondary = OliveGreen_md_theme_light_secondary,
                        lightSecondaryContainer = OliveGreen_md_theme_light_secondaryContainer,
                        darkSecondary = OliveGreen_md_theme_dark_secondary,
                        darkSecondaryContainer = OliveGreen_md_theme_dark_secondaryContainer,
                        lightTertiary = OliveGreen_md_theme_light_tertiary,
                        lightTertiaryContainer = OliveGreen_md_theme_light_tertiaryContainer,
                        darkTertiary = OliveGreen_md_theme_dark_tertiary,
                        darkTertiaryContainer = OliveGreen_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "申布伦黄",
                        themeName = THEME_NAME_SCHENBRUNN_YELLOW,
                        isSelected = themeName == THEME_NAME_SCHENBRUNN_YELLOW,
                        lightPrimary = Schenbrunn_Yellowmd_md_theme_light_primary,
                        lightPrimaryContainer = Schenbrunn_Yellowmd_md_theme_light_primaryContainer,
                        darkPrimary = Schenbrunn_Yellowmd_md_theme_dark_primary,
                        darkPrimaryContainer = Schenbrunn_Yellowmd_md_theme_dark_primaryContainer,
                        lightSecondary = Schenbrunn_Yellowmd_md_theme_light_secondary,
                        lightSecondaryContainer = Schenbrunn_Yellowmd_md_theme_light_secondaryContainer,
                        darkSecondary = Schenbrunn_Yellowmd_md_theme_dark_secondary,
                        darkSecondaryContainer = Schenbrunn_Yellowmd_md_theme_dark_secondaryContainer,
                        lightTertiary = Schenbrunn_Yellowmd_md_theme_light_tertiary,
                        lightTertiaryContainer = Schenbrunn_Yellowmd_md_theme_light_tertiaryContainer,
                        darkTertiary = Schenbrunn_Yellowmd_md_theme_dark_tertiary,
                        darkTertiaryContainer = Schenbrunn_Yellowmd_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "提香红",
                        themeName = THEME_NAME_TITIAN_RED,
                        isSelected = themeName == THEME_NAME_TITIAN_RED,
                        lightPrimary = TitianRed_md_theme_light_primary,
                        lightPrimaryContainer = TitianRed_md_theme_light_primaryContainer,
                        darkPrimary = TitianRed_md_theme_dark_primary,
                        darkPrimaryContainer = TitianRed_md_theme_dark_primaryContainer,
                        lightSecondary = TitianRed_md_theme_light_secondary,
                        lightSecondaryContainer = TitianRed_md_theme_light_secondaryContainer,
                        darkSecondary = TitianRed_md_theme_dark_secondary,
                        darkSecondaryContainer = TitianRed_md_theme_dark_secondaryContainer,
                        lightTertiary = TitianRed_md_theme_light_tertiary,
                        lightTertiaryContainer = TitianRed_md_theme_light_tertiaryContainer,
                        darkTertiary = TitianRed_md_theme_dark_tertiary,
                        darkTertiaryContainer = TitianRed_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "混凝土灰",
                        themeName = THEME_NAME_CONCRETE_GRAY,
                        isSelected = themeName == THEME_NAME_CONCRETE_GRAY,
                        isNeedVip = false,
                        lightPrimary = Concrete_md_theme_light_primary,
                        lightPrimaryContainer = Concrete_md_theme_light_primaryContainer,
                        darkPrimary = Concrete_md_theme_dark_primary,
                        darkPrimaryContainer = Concrete_md_theme_dark_primaryContainer,
                        lightSecondary = Concrete_md_theme_light_secondary,
                        lightSecondaryContainer = Concrete_md_theme_light_secondaryContainer,
                        darkSecondary = Concrete_md_theme_dark_secondary,
                        darkSecondaryContainer = Concrete_md_theme_dark_secondaryContainer,
                        lightTertiary = Concrete_md_theme_light_tertiary,
                        lightTertiaryContainer = Concrete_md_theme_light_tertiaryContainer,
                        darkTertiary = Concrete_md_theme_dark_tertiary,
                        darkTertiaryContainer = Concrete_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "暖砂米白",
                        themeName = THEME_NAME_SANDSTONE_BEIGE,
                        isSelected = themeName == THEME_NAME_SANDSTONE_BEIGE,
                        isNeedVip = false,
                        lightPrimary = Sandstone_md_theme_light_primary,
                        lightPrimaryContainer = Sandstone_md_theme_light_primaryContainer,
                        darkPrimary = Sandstone_md_theme_dark_primary,
                        darkPrimaryContainer = Sandstone_md_theme_dark_primaryContainer,
                        lightSecondary = Sandstone_md_theme_light_secondary,
                        lightSecondaryContainer = Sandstone_md_theme_light_secondaryContainer,
                        darkSecondary = Sandstone_md_theme_dark_secondary,
                        darkSecondaryContainer = Sandstone_md_theme_dark_secondaryContainer,
                        lightTertiary = Sandstone_md_theme_light_tertiary,
                        lightTertiaryContainer = Sandstone_md_theme_light_tertiaryContainer,
                        darkTertiary = Sandstone_md_theme_dark_tertiary,
                        darkTertiaryContainer = Sandstone_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "墨蓝石板",
                        themeName = THEME_NAME_INK_SLATE,
                        isSelected = themeName == THEME_NAME_INK_SLATE,
                        isNeedVip = false,
                        lightPrimary = InkSlate_md_theme_light_primary,
                        lightPrimaryContainer = InkSlate_md_theme_light_primaryContainer,
                        darkPrimary = InkSlate_md_theme_dark_primary,
                        darkPrimaryContainer = InkSlate_md_theme_dark_primaryContainer,
                        lightSecondary = InkSlate_md_theme_light_secondary,
                        lightSecondaryContainer = InkSlate_md_theme_light_secondaryContainer,
                        darkSecondary = InkSlate_md_theme_dark_secondary,
                        darkSecondaryContainer = InkSlate_md_theme_dark_secondaryContainer,
                        lightTertiary = InkSlate_md_theme_light_tertiary,
                        lightTertiaryContainer = InkSlate_md_theme_light_tertiaryContainer,
                        darkTertiary = InkSlate_md_theme_dark_tertiary,
                        darkTertiaryContainer = InkSlate_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "黛紫",
                        themeName = THEME_NAME_AMETHYST,
                        isSelected = themeName == THEME_NAME_AMETHYST,
                        isNeedVip = false,
                        lightPrimary = Amethyst_md_theme_light_primary,
                        lightPrimaryContainer = Amethyst_md_theme_light_primaryContainer,
                        darkPrimary = Amethyst_md_theme_dark_primary,
                        darkPrimaryContainer = Amethyst_md_theme_dark_primaryContainer,
                        lightSecondary = Amethyst_md_theme_light_secondary,
                        lightSecondaryContainer = Amethyst_md_theme_light_secondaryContainer,
                        darkSecondary = Amethyst_md_theme_dark_secondary,
                        darkSecondaryContainer = Amethyst_md_theme_dark_secondaryContainer,
                        lightTertiary = Amethyst_md_theme_light_tertiary,
                        lightTertiaryContainer = Amethyst_md_theme_light_tertiaryContainer,
                        darkTertiary = Amethyst_md_theme_dark_tertiary,
                        darkTertiaryContainer = Amethyst_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "晨雾青瓷",
                        themeName = THEME_NAME_CELADON,
                        isSelected = themeName == THEME_NAME_CELADON,
                        isNeedVip = false,
                        lightPrimary = Celadon_md_theme_light_primary,
                        lightPrimaryContainer = Celadon_md_theme_light_primaryContainer,
                        darkPrimary = Celadon_md_theme_dark_primary,
                        darkPrimaryContainer = Celadon_md_theme_dark_primaryContainer,
                        lightSecondary = Celadon_md_theme_light_secondary,
                        lightSecondaryContainer = Celadon_md_theme_light_secondaryContainer,
                        darkSecondary = Celadon_md_theme_dark_secondary,
                        darkSecondaryContainer = Celadon_md_theme_dark_secondaryContainer,
                        lightTertiary = Celadon_md_theme_light_tertiary,
                        lightTertiaryContainer = Celadon_md_theme_light_tertiaryContainer,
                        darkTertiary = Celadon_md_theme_dark_tertiary,
                        darkTertiaryContainer = Celadon_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "香槟金",
                        themeName = THEME_NAME_CHAMPAGNE,
                        isSelected = themeName == THEME_NAME_CHAMPAGNE,
                        isNeedVip = false,
                        lightPrimary = Champagne_md_theme_light_primary,
                        lightPrimaryContainer = Champagne_md_theme_light_primaryContainer,
                        darkPrimary = Champagne_md_theme_dark_primary,
                        darkPrimaryContainer = Champagne_md_theme_dark_primaryContainer,
                        lightSecondary = Champagne_md_theme_light_secondary,
                        lightSecondaryContainer = Champagne_md_theme_light_secondaryContainer,
                        darkSecondary = Champagne_md_theme_dark_secondary,
                        darkSecondaryContainer = Champagne_md_theme_dark_secondaryContainer,
                        lightTertiary = Champagne_md_theme_light_tertiary,
                        lightTertiaryContainer = Champagne_md_theme_light_tertiaryContainer,
                        darkTertiary = Champagne_md_theme_dark_tertiary,
                        darkTertiaryContainer = Champagne_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "玻璃幕墙",
                        themeName = THEME_NAME_GLASS_TOWER,
                        isSelected = themeName == THEME_NAME_GLASS_TOWER,
                        isNeedVip = false,
                        lightPrimary = GlassTower_md_theme_light_primary,
                        lightPrimaryContainer = GlassTower_md_theme_light_primaryContainer,
                        darkPrimary = GlassTower_md_theme_dark_primary,
                        darkPrimaryContainer = GlassTower_md_theme_dark_primaryContainer,
                        lightSecondary = GlassTower_md_theme_light_secondary,
                        lightSecondaryContainer = GlassTower_md_theme_light_secondaryContainer,
                        darkSecondary = GlassTower_md_theme_dark_secondary,
                        darkSecondaryContainer = GlassTower_md_theme_dark_secondaryContainer,
                        lightTertiary = GlassTower_md_theme_light_tertiary,
                        lightTertiaryContainer = GlassTower_md_theme_light_tertiaryContainer,
                        darkTertiary = GlassTower_md_theme_dark_tertiary,
                        darkTertiaryContainer = GlassTower_md_theme_dark_tertiaryContainer,
                    ),
                    ThemeNormalSelectItemVo(
                        name = "包豪斯",
                        themeName = THEME_NAME_BAUHAUS,
                        isSelected = themeName == THEME_NAME_BAUHAUS,
                        isNeedVip = false,
                        lightPrimary = Bauhaus_md_theme_light_primary,
                        lightPrimaryContainer = Bauhaus_md_theme_light_primaryContainer,
                        darkPrimary = Bauhaus_md_theme_dark_primary,
                        darkPrimaryContainer = Bauhaus_md_theme_dark_primaryContainer,
                        lightSecondary = Bauhaus_md_theme_light_secondary,
                        lightSecondaryContainer = Bauhaus_md_theme_light_secondaryContainer,
                        darkSecondary = Bauhaus_md_theme_dark_secondary,
                        darkSecondaryContainer = Bauhaus_md_theme_dark_secondaryContainer,
                        lightTertiary = Bauhaus_md_theme_light_tertiary,
                        lightTertiaryContainer = Bauhaus_md_theme_light_tertiaryContainer,
                        darkTertiary = Bauhaus_md_theme_dark_tertiary,
                        darkTertiaryContainer = Bauhaus_md_theme_dark_tertiaryContainer,
                    ),
                ).run {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        this
                    } else {
                        this.filter {
                            it.themeName != THEME_NAME_FOLLOW_SYSTEM
                        }
                    }
                }
            )
        }

}