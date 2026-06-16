package com.xiaojinzi.tally.lib.res.ui.theme.sandstone

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 暖砂米白: 暖调砂岩/原木 + 米白背景, 日式侘寂建筑风

val Sandstone_md_theme_light_primary = Color(0xFF8A6E4B)
val Sandstone_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val Sandstone_md_theme_light_primaryContainer = Color(0xFFF4E2C8)
val Sandstone_md_theme_light_onPrimaryContainer = Color(0xFF2E1F08)
val Sandstone_md_theme_light_secondary = Color(0xFF7C7259)
val Sandstone_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val Sandstone_md_theme_light_secondaryContainer = Color(0xFFEBE2CC)
val Sandstone_md_theme_light_onSecondaryContainer = Color(0xFF271E0C)
val Sandstone_md_theme_light_tertiary = Color(0xFF5C6657)
val Sandstone_md_theme_light_onTertiary = Color(0xFFFFFFFF)
val Sandstone_md_theme_light_tertiaryContainer = Color(0xFFDEE9D6)
val Sandstone_md_theme_light_onTertiaryContainer = Color(0xFF192217)
val Sandstone_md_theme_light_background = Color(0xFFF7F2E9)
val Sandstone_md_theme_light_onBackground = Color(0xFF1E1B14)
val Sandstone_md_theme_light_surface = Color(0xFFFCF8F0)
val Sandstone_md_theme_light_onSurface = Color(0xFF1E1B14)
val Sandstone_md_theme_light_surfaceVariant = Color(0xFFECE1CF)
val Sandstone_md_theme_light_onSurfaceVariant = Color(0xFF4C463A)
val Sandstone_md_theme_light_outline = Color(0xFF7E7768)

val Sandstone_md_theme_dark_primary = Color(0xFFDEBE93)
val Sandstone_md_theme_dark_onPrimary = Color(0xFF3D2D11)
val Sandstone_md_theme_dark_primaryContainer = Color(0xFF6E5535)
val Sandstone_md_theme_dark_onPrimaryContainer = Color(0xFFF4E2C8)
val Sandstone_md_theme_dark_secondary = Color(0xFFCFC6AB)
val Sandstone_md_theme_dark_onSecondary = Color(0xFF2E2818)
val Sandstone_md_theme_dark_secondaryContainer = Color(0xFF443D2D)
val Sandstone_md_theme_dark_onSecondaryContainer = Color(0xFFEBE2CC)
val Sandstone_md_theme_dark_tertiary = Color(0xFFC2CDB6)
val Sandstone_md_theme_dark_onTertiary = Color(0xFF2C3626)
val Sandstone_md_theme_dark_tertiaryContainer = Color(0xFF424D3B)
val Sandstone_md_theme_dark_onTertiaryContainer = Color(0xFFDEE9D6)
val Sandstone_md_theme_dark_background = Color(0xFF16130D)
val Sandstone_md_theme_dark_onBackground = Color(0xFFECE1D3)
val Sandstone_md_theme_dark_surface = Color(0xFF16130D)
val Sandstone_md_theme_dark_onSurface = Color(0xFFECE1D3)
val Sandstone_md_theme_dark_surfaceVariant = Color(0xFF4C463A)
val Sandstone_md_theme_dark_onSurfaceVariant = Color(0xFFCFC6B3)
val Sandstone_md_theme_dark_outline = Color(0xFF999080)

private val SandstoneLightColors = lightColorScheme(
    primary = Sandstone_md_theme_light_primary,
    onPrimary = Sandstone_md_theme_light_onPrimary,
    primaryContainer = Sandstone_md_theme_light_primaryContainer,
    onPrimaryContainer = Sandstone_md_theme_light_onPrimaryContainer,
    secondary = Sandstone_md_theme_light_secondary,
    onSecondary = Sandstone_md_theme_light_onSecondary,
    secondaryContainer = Sandstone_md_theme_light_secondaryContainer,
    onSecondaryContainer = Sandstone_md_theme_light_onSecondaryContainer,
    tertiary = Sandstone_md_theme_light_tertiary,
    onTertiary = Sandstone_md_theme_light_onTertiary,
    tertiaryContainer = Sandstone_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Sandstone_md_theme_light_onTertiaryContainer,
    background = Sandstone_md_theme_light_background,
    onBackground = Sandstone_md_theme_light_onBackground,
    surface = Sandstone_md_theme_light_surface,
    onSurface = Sandstone_md_theme_light_onSurface,
    surfaceVariant = Sandstone_md_theme_light_surfaceVariant,
    onSurfaceVariant = Sandstone_md_theme_light_onSurfaceVariant,
    outline = Sandstone_md_theme_light_outline,
)

private val SandstoneDarkColors = darkColorScheme(
    primary = Sandstone_md_theme_dark_primary,
    onPrimary = Sandstone_md_theme_dark_onPrimary,
    primaryContainer = Sandstone_md_theme_dark_primaryContainer,
    onPrimaryContainer = Sandstone_md_theme_dark_onPrimaryContainer,
    secondary = Sandstone_md_theme_dark_secondary,
    onSecondary = Sandstone_md_theme_dark_onSecondary,
    secondaryContainer = Sandstone_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Sandstone_md_theme_dark_onSecondaryContainer,
    tertiary = Sandstone_md_theme_dark_tertiary,
    onTertiary = Sandstone_md_theme_dark_onTertiary,
    tertiaryContainer = Sandstone_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Sandstone_md_theme_dark_onTertiaryContainer,
    background = Sandstone_md_theme_dark_background,
    onBackground = Sandstone_md_theme_dark_onBackground,
    surface = Sandstone_md_theme_dark_surface,
    onSurface = Sandstone_md_theme_dark_onSurface,
    surfaceVariant = Sandstone_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Sandstone_md_theme_dark_onSurfaceVariant,
    outline = Sandstone_md_theme_dark_outline,
)

@Composable
fun SandstoneBeigeAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) SandstoneDarkColors else SandstoneLightColors,
        content = content,
    )
}
