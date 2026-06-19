package com.xiaojinzi.tally.lib.res.ui.theme.glasstower

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 玻璃幕墙 Glass Tower: 摩天楼冷蓝 + 青玻璃强调, 通透现代

val GlassTower_md_theme_light_primary = Color(0xFF2E5A8F)
val GlassTower_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val GlassTower_md_theme_light_primaryContainer = Color(0xFFD3E3F7)
val GlassTower_md_theme_light_onPrimaryContainer = Color(0xFF0A1F33)
val GlassTower_md_theme_light_secondary = Color(0xFF4E6079)
val GlassTower_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val GlassTower_md_theme_light_secondaryContainer = Color(0xFFDCE6F2)
val GlassTower_md_theme_light_onSecondaryContainer = Color(0xFF111C28)
val GlassTower_md_theme_light_tertiary = Color(0xFF2A8DA3)
val GlassTower_md_theme_light_onTertiary = Color(0xFFFFFFFF)
val GlassTower_md_theme_light_tertiaryContainer = Color(0xFFBCEAF3)
val GlassTower_md_theme_light_onTertiaryContainer = Color(0xFF062530)
val GlassTower_md_theme_light_background = Color(0xFFF2F5F8)
val GlassTower_md_theme_light_onBackground = Color(0xFF1A1C1E)
val GlassTower_md_theme_light_surface = Color(0xFFF8FAFC)
val GlassTower_md_theme_light_onSurface = Color(0xFF1A1C1E)
val GlassTower_md_theme_light_surfaceVariant = Color(0xFFDCE3EB)
val GlassTower_md_theme_light_onSurfaceVariant = Color(0xFF41484F)
val GlassTower_md_theme_light_outline = Color(0xFF71787F)

val GlassTower_md_theme_dark_primary = Color(0xFFA6C8F0)
val GlassTower_md_theme_dark_onPrimary = Color(0xFF0A2F52)
val GlassTower_md_theme_dark_primaryContainer = Color(0xFF1A446E)
val GlassTower_md_theme_dark_onPrimaryContainer = Color(0xFFD3E3F7)
val GlassTower_md_theme_dark_secondary = Color(0xFFB6C7DD)
val GlassTower_md_theme_dark_onSecondary = Color(0xFF25313F)
val GlassTower_md_theme_dark_secondaryContainer = Color(0xFF38485A)
val GlassTower_md_theme_dark_onSecondaryContainer = Color(0xFFDCE6F2)
val GlassTower_md_theme_dark_tertiary = Color(0xFF7FD0E3)
val GlassTower_md_theme_dark_onTertiary = Color(0xFF00363F)
val GlassTower_md_theme_dark_tertiaryContainer = Color(0xFF0E4F5C)
val GlassTower_md_theme_dark_onTertiaryContainer = Color(0xFFBCEAF3)
val GlassTower_md_theme_dark_background = Color(0xFF122230)
val GlassTower_md_theme_dark_onBackground = Color(0xFFE1E2E5)
val GlassTower_md_theme_dark_surface = Color(0xFF122230)
val GlassTower_md_theme_dark_onSurface = Color(0xFFE1E2E5)
val GlassTower_md_theme_dark_surfaceVariant = Color(0xFF41484F)
val GlassTower_md_theme_dark_onSurfaceVariant = Color(0xFFC1C7CE)
val GlassTower_md_theme_dark_outline = Color(0xFF8B9298)

private val GlassTowerLightColors = lightColorScheme(
    primary = GlassTower_md_theme_light_primary,
    onPrimary = GlassTower_md_theme_light_onPrimary,
    primaryContainer = GlassTower_md_theme_light_primaryContainer,
    onPrimaryContainer = GlassTower_md_theme_light_onPrimaryContainer,
    secondary = GlassTower_md_theme_light_secondary,
    onSecondary = GlassTower_md_theme_light_onSecondary,
    secondaryContainer = GlassTower_md_theme_light_secondaryContainer,
    onSecondaryContainer = GlassTower_md_theme_light_onSecondaryContainer,
    tertiary = GlassTower_md_theme_light_tertiary,
    onTertiary = GlassTower_md_theme_light_onTertiary,
    tertiaryContainer = GlassTower_md_theme_light_tertiaryContainer,
    onTertiaryContainer = GlassTower_md_theme_light_onTertiaryContainer,
    background = GlassTower_md_theme_light_background,
    onBackground = GlassTower_md_theme_light_onBackground,
    surface = GlassTower_md_theme_light_surface,
    onSurface = GlassTower_md_theme_light_onSurface,
    surfaceVariant = GlassTower_md_theme_light_surfaceVariant,
    onSurfaceVariant = GlassTower_md_theme_light_onSurfaceVariant,
    outline = GlassTower_md_theme_light_outline,
)

private val GlassTowerDarkColors = darkColorScheme(
    primary = GlassTower_md_theme_dark_primary,
    onPrimary = GlassTower_md_theme_dark_onPrimary,
    primaryContainer = GlassTower_md_theme_dark_primaryContainer,
    onPrimaryContainer = GlassTower_md_theme_dark_onPrimaryContainer,
    secondary = GlassTower_md_theme_dark_secondary,
    onSecondary = GlassTower_md_theme_dark_onSecondary,
    secondaryContainer = GlassTower_md_theme_dark_secondaryContainer,
    onSecondaryContainer = GlassTower_md_theme_dark_onSecondaryContainer,
    tertiary = GlassTower_md_theme_dark_tertiary,
    onTertiary = GlassTower_md_theme_dark_onTertiary,
    tertiaryContainer = GlassTower_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = GlassTower_md_theme_dark_onTertiaryContainer,
    background = GlassTower_md_theme_dark_background,
    onBackground = GlassTower_md_theme_dark_onBackground,
    surface = GlassTower_md_theme_dark_surface,
    onSurface = GlassTower_md_theme_dark_onSurface,
    surfaceVariant = GlassTower_md_theme_dark_surfaceVariant,
    onSurfaceVariant = GlassTower_md_theme_dark_onSurfaceVariant,
    outline = GlassTower_md_theme_dark_outline,
)

@Composable
fun GlassTowerAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) GlassTowerDarkColors else GlassTowerLightColors,
        content = content,
    )
}
