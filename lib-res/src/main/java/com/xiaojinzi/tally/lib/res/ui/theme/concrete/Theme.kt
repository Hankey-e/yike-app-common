package com.xiaojinzi.tally.lib.res.ui.theme.concrete

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 混凝土灰: 冷调中性灰 + 清水混凝土质感, 极简建筑风

val Concrete_md_theme_light_primary = Color(0xFF4F5B66)
val Concrete_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val Concrete_md_theme_light_primaryContainer = Color(0xFFD6DEE6)
val Concrete_md_theme_light_onPrimaryContainer = Color(0xFF0C1A26)
val Concrete_md_theme_light_secondary = Color(0xFF5E6A72)
val Concrete_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val Concrete_md_theme_light_secondaryContainer = Color(0xFFE1E7EB)
val Concrete_md_theme_light_onSecondaryContainer = Color(0xFF1A2227)
val Concrete_md_theme_light_tertiary = Color(0xFF6B6F73)
val Concrete_md_theme_light_onTertiary = Color(0xFFFFFFFF)
val Concrete_md_theme_light_tertiaryContainer = Color(0xFFE6E8EA)
val Concrete_md_theme_light_onTertiaryContainer = Color(0xFF202224)
val Concrete_md_theme_light_background = Color(0xFFF2F3F4)
val Concrete_md_theme_light_onBackground = Color(0xFF1A1C1E)
val Concrete_md_theme_light_surface = Color(0xFFFAFAFB)
val Concrete_md_theme_light_onSurface = Color(0xFF1A1C1E)
val Concrete_md_theme_light_surfaceVariant = Color(0xFFDFE2E5)
val Concrete_md_theme_light_onSurfaceVariant = Color(0xFF43474B)
val Concrete_md_theme_light_outline = Color(0xFF73777C)

val Concrete_md_theme_dark_primary = Color(0xFFB6C3CF)
val Concrete_md_theme_dark_onPrimary = Color(0xFF213039)
val Concrete_md_theme_dark_primaryContainer = Color(0xFF38454F)
val Concrete_md_theme_dark_onPrimaryContainer = Color(0xFFD6DEE6)
val Concrete_md_theme_dark_secondary = Color(0xFFC2CDD5)
val Concrete_md_theme_dark_onSecondary = Color(0xFF2C363C)
val Concrete_md_theme_dark_secondaryContainer = Color(0xFF434D54)
val Concrete_md_theme_dark_onSecondaryContainer = Color(0xFFDEE7EC)
val Concrete_md_theme_dark_tertiary = Color(0xFFC7C9CC)
val Concrete_md_theme_dark_onTertiary = Color(0xFF2F3133)
val Concrete_md_theme_dark_tertiaryContainer = Color(0xFF454749)
val Concrete_md_theme_dark_onTertiaryContainer = Color(0xFFE6E8EA)
val Concrete_md_theme_dark_background = Color(0xFF1A1C1E)
val Concrete_md_theme_dark_onBackground = Color(0xFFE3E2E4)
val Concrete_md_theme_dark_surface = Color(0xFF1A1C1E)
val Concrete_md_theme_dark_onSurface = Color(0xFFE3E2E4)
val Concrete_md_theme_dark_surfaceVariant = Color(0xFF43474B)
val Concrete_md_theme_dark_onSurfaceVariant = Color(0xFFC3C7CC)
val Concrete_md_theme_dark_outline = Color(0xFF8D9196)

private val ConcreteLightColors = lightColorScheme(
    primary = Concrete_md_theme_light_primary,
    onPrimary = Concrete_md_theme_light_onPrimary,
    primaryContainer = Concrete_md_theme_light_primaryContainer,
    onPrimaryContainer = Concrete_md_theme_light_onPrimaryContainer,
    secondary = Concrete_md_theme_light_secondary,
    onSecondary = Concrete_md_theme_light_onSecondary,
    secondaryContainer = Concrete_md_theme_light_secondaryContainer,
    onSecondaryContainer = Concrete_md_theme_light_onSecondaryContainer,
    tertiary = Concrete_md_theme_light_tertiary,
    onTertiary = Concrete_md_theme_light_onTertiary,
    tertiaryContainer = Concrete_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Concrete_md_theme_light_onTertiaryContainer,
    background = Concrete_md_theme_light_background,
    onBackground = Concrete_md_theme_light_onBackground,
    surface = Concrete_md_theme_light_surface,
    onSurface = Concrete_md_theme_light_onSurface,
    surfaceVariant = Concrete_md_theme_light_surfaceVariant,
    onSurfaceVariant = Concrete_md_theme_light_onSurfaceVariant,
    outline = Concrete_md_theme_light_outline,
)

private val ConcreteDarkColors = darkColorScheme(
    primary = Concrete_md_theme_dark_primary,
    onPrimary = Concrete_md_theme_dark_onPrimary,
    primaryContainer = Concrete_md_theme_dark_primaryContainer,
    onPrimaryContainer = Concrete_md_theme_dark_onPrimaryContainer,
    secondary = Concrete_md_theme_dark_secondary,
    onSecondary = Concrete_md_theme_dark_onSecondary,
    secondaryContainer = Concrete_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Concrete_md_theme_dark_onSecondaryContainer,
    tertiary = Concrete_md_theme_dark_tertiary,
    onTertiary = Concrete_md_theme_dark_onTertiary,
    tertiaryContainer = Concrete_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Concrete_md_theme_dark_onTertiaryContainer,
    background = Concrete_md_theme_dark_background,
    onBackground = Concrete_md_theme_dark_onBackground,
    surface = Concrete_md_theme_dark_surface,
    onSurface = Concrete_md_theme_dark_onSurface,
    surfaceVariant = Concrete_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Concrete_md_theme_dark_onSurfaceVariant,
    outline = Concrete_md_theme_dark_outline,
)

@Composable
fun ConcreteGrayAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) ConcreteDarkColors else ConcreteLightColors,
        content = content,
    )
}
