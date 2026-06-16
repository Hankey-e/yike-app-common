package com.xiaojinzi.tally.lib.res.ui.theme.inkslate

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 墨蓝石板: 冷调深蓝灰 + 钢构玻璃感, 现代建筑风

val InkSlate_md_theme_light_primary = Color(0xFF2F4A63)
val InkSlate_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val InkSlate_md_theme_light_primaryContainer = Color(0xFFCCE0F4)
val InkSlate_md_theme_light_onPrimaryContainer = Color(0xFF0A1B2A)
val InkSlate_md_theme_light_secondary = Color(0xFF4F606E)
val InkSlate_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val InkSlate_md_theme_light_secondaryContainer = Color(0xFFD6E2EE)
val InkSlate_md_theme_light_onSecondaryContainer = Color(0xFF0C1A24)
val InkSlate_md_theme_light_tertiary = Color(0xFF5B5C6E)
val InkSlate_md_theme_light_onTertiary = Color(0xFFFFFFFF)
val InkSlate_md_theme_light_tertiaryContainer = Color(0xFFE0E0F0)
val InkSlate_md_theme_light_onTertiaryContainer = Color(0xFF181925)
val InkSlate_md_theme_light_background = Color(0xFFF2F4F7)
val InkSlate_md_theme_light_onBackground = Color(0xFF191C1F)
val InkSlate_md_theme_light_surface = Color(0xFFFAFBFC)
val InkSlate_md_theme_light_onSurface = Color(0xFF191C1F)
val InkSlate_md_theme_light_surfaceVariant = Color(0xFFDBE3EB)
val InkSlate_md_theme_light_onSurfaceVariant = Color(0xFF40484E)
val InkSlate_md_theme_light_outline = Color(0xFF70787E)

val InkSlate_md_theme_dark_primary = Color(0xFF9FC9EC)
val InkSlate_md_theme_dark_onPrimary = Color(0xFF00344F)
val InkSlate_md_theme_dark_primaryContainer = Color(0xFF163B57)
val InkSlate_md_theme_dark_onPrimaryContainer = Color(0xFFCCE0F4)
val InkSlate_md_theme_dark_secondary = Color(0xFFB6C7D6)
val InkSlate_md_theme_dark_onSecondary = Color(0xFF21323D)
val InkSlate_md_theme_dark_secondaryContainer = Color(0xFF384956)
val InkSlate_md_theme_dark_onSecondaryContainer = Color(0xFFD6E2EE)
val InkSlate_md_theme_dark_tertiary = Color(0xFFC3C4D9)
val InkSlate_md_theme_dark_onTertiary = Color(0xFF2D2E3F)
val InkSlate_md_theme_dark_tertiaryContainer = Color(0xFF434457)
val InkSlate_md_theme_dark_onTertiaryContainer = Color(0xFFE0E0F0)
val InkSlate_md_theme_dark_background = Color(0xFF11151A)
val InkSlate_md_theme_dark_onBackground = Color(0xFFE1E2E5)
val InkSlate_md_theme_dark_surface = Color(0xFF11151A)
val InkSlate_md_theme_dark_onSurface = Color(0xFFE1E2E5)
val InkSlate_md_theme_dark_surfaceVariant = Color(0xFF40484E)
val InkSlate_md_theme_dark_onSurfaceVariant = Color(0xFFC0C8CF)
val InkSlate_md_theme_dark_outline = Color(0xFF8A929A)

private val InkSlateLightColors = lightColorScheme(
    primary = InkSlate_md_theme_light_primary,
    onPrimary = InkSlate_md_theme_light_onPrimary,
    primaryContainer = InkSlate_md_theme_light_primaryContainer,
    onPrimaryContainer = InkSlate_md_theme_light_onPrimaryContainer,
    secondary = InkSlate_md_theme_light_secondary,
    onSecondary = InkSlate_md_theme_light_onSecondary,
    secondaryContainer = InkSlate_md_theme_light_secondaryContainer,
    onSecondaryContainer = InkSlate_md_theme_light_onSecondaryContainer,
    tertiary = InkSlate_md_theme_light_tertiary,
    onTertiary = InkSlate_md_theme_light_onTertiary,
    tertiaryContainer = InkSlate_md_theme_light_tertiaryContainer,
    onTertiaryContainer = InkSlate_md_theme_light_onTertiaryContainer,
    background = InkSlate_md_theme_light_background,
    onBackground = InkSlate_md_theme_light_onBackground,
    surface = InkSlate_md_theme_light_surface,
    onSurface = InkSlate_md_theme_light_onSurface,
    surfaceVariant = InkSlate_md_theme_light_surfaceVariant,
    onSurfaceVariant = InkSlate_md_theme_light_onSurfaceVariant,
    outline = InkSlate_md_theme_light_outline,
)

private val InkSlateDarkColors = darkColorScheme(
    primary = InkSlate_md_theme_dark_primary,
    onPrimary = InkSlate_md_theme_dark_onPrimary,
    primaryContainer = InkSlate_md_theme_dark_primaryContainer,
    onPrimaryContainer = InkSlate_md_theme_dark_onPrimaryContainer,
    secondary = InkSlate_md_theme_dark_secondary,
    onSecondary = InkSlate_md_theme_dark_onSecondary,
    secondaryContainer = InkSlate_md_theme_dark_secondaryContainer,
    onSecondaryContainer = InkSlate_md_theme_dark_onSecondaryContainer,
    tertiary = InkSlate_md_theme_dark_tertiary,
    onTertiary = InkSlate_md_theme_dark_onTertiary,
    tertiaryContainer = InkSlate_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = InkSlate_md_theme_dark_onTertiaryContainer,
    background = InkSlate_md_theme_dark_background,
    onBackground = InkSlate_md_theme_dark_onBackground,
    surface = InkSlate_md_theme_dark_surface,
    onSurface = InkSlate_md_theme_dark_onSurface,
    surfaceVariant = InkSlate_md_theme_dark_surfaceVariant,
    onSurfaceVariant = InkSlate_md_theme_dark_onSurfaceVariant,
    outline = InkSlate_md_theme_dark_outline,
)

@Composable
fun InkSlateAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) InkSlateDarkColors else InkSlateLightColors,
        content = content,
    )
}
