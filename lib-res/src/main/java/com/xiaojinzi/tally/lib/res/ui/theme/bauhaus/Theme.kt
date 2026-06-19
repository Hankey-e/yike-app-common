package com.xiaojinzi.tally.lib.res.ui.theme.bauhaus

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 包豪斯 Bauhaus: 几何原色构成 — 朱红(主) + 钴蓝(次) + 明黄(点缀), 象牙白 / 近黑

val Bauhaus_md_theme_light_primary = Color(0xFFD7263D)
val Bauhaus_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val Bauhaus_md_theme_light_primaryContainer = Color(0xFFFFDAD9)
val Bauhaus_md_theme_light_onPrimaryContainer = Color(0xFF410006)
val Bauhaus_md_theme_light_secondary = Color(0xFF1B4D8F)
val Bauhaus_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val Bauhaus_md_theme_light_secondaryContainer = Color(0xFFD6E3FF)
val Bauhaus_md_theme_light_onSecondaryContainer = Color(0xFF001A41)
val Bauhaus_md_theme_light_tertiary = Color(0xFFF2A900)
val Bauhaus_md_theme_light_onTertiary = Color(0xFF1A1A1A)
val Bauhaus_md_theme_light_tertiaryContainer = Color(0xFFFFE08A)
val Bauhaus_md_theme_light_onTertiaryContainer = Color(0xFF2A1C00)
val Bauhaus_md_theme_light_background = Color(0xFFF7F5F0)
val Bauhaus_md_theme_light_onBackground = Color(0xFF1A1A1A)
val Bauhaus_md_theme_light_surface = Color(0xFFFBF9F4)
val Bauhaus_md_theme_light_onSurface = Color(0xFF1A1A1A)
val Bauhaus_md_theme_light_surfaceVariant = Color(0xFFE5E1D8)
val Bauhaus_md_theme_light_onSurfaceVariant = Color(0xFF48473F)
val Bauhaus_md_theme_light_outline = Color(0xFF7A7869)

val Bauhaus_md_theme_dark_primary = Color(0xFFFFB3AE)
val Bauhaus_md_theme_dark_onPrimary = Color(0xFF680012)
val Bauhaus_md_theme_dark_primaryContainer = Color(0xFF8C1D2A)
val Bauhaus_md_theme_dark_onPrimaryContainer = Color(0xFFFFDAD9)
val Bauhaus_md_theme_dark_secondary = Color(0xFFABC7FF)
val Bauhaus_md_theme_dark_onSecondary = Color(0xFF002E69)
val Bauhaus_md_theme_dark_secondaryContainer = Color(0xFF1C447C)
val Bauhaus_md_theme_dark_onSecondaryContainer = Color(0xFFD6E3FF)
val Bauhaus_md_theme_dark_tertiary = Color(0xFFFFC74D)
val Bauhaus_md_theme_dark_onTertiary = Color(0xFF3F2E00)
val Bauhaus_md_theme_dark_tertiaryContainer = Color(0xFF5C4300)
val Bauhaus_md_theme_dark_onTertiaryContainer = Color(0xFFFFE08A)
val Bauhaus_md_theme_dark_background = Color(0xFF1A1A1A)
val Bauhaus_md_theme_dark_onBackground = Color(0xFFE6E2DA)
val Bauhaus_md_theme_dark_surface = Color(0xFF1A1A1A)
val Bauhaus_md_theme_dark_onSurface = Color(0xFFE6E2DA)
val Bauhaus_md_theme_dark_surfaceVariant = Color(0xFF48473F)
val Bauhaus_md_theme_dark_onSurfaceVariant = Color(0xFFC9C5BA)
val Bauhaus_md_theme_dark_outline = Color(0xFF93917F)

private val BauhausLightColors = lightColorScheme(
    primary = Bauhaus_md_theme_light_primary,
    onPrimary = Bauhaus_md_theme_light_onPrimary,
    primaryContainer = Bauhaus_md_theme_light_primaryContainer,
    onPrimaryContainer = Bauhaus_md_theme_light_onPrimaryContainer,
    secondary = Bauhaus_md_theme_light_secondary,
    onSecondary = Bauhaus_md_theme_light_onSecondary,
    secondaryContainer = Bauhaus_md_theme_light_secondaryContainer,
    onSecondaryContainer = Bauhaus_md_theme_light_onSecondaryContainer,
    tertiary = Bauhaus_md_theme_light_tertiary,
    onTertiary = Bauhaus_md_theme_light_onTertiary,
    tertiaryContainer = Bauhaus_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Bauhaus_md_theme_light_onTertiaryContainer,
    background = Bauhaus_md_theme_light_background,
    onBackground = Bauhaus_md_theme_light_onBackground,
    surface = Bauhaus_md_theme_light_surface,
    onSurface = Bauhaus_md_theme_light_onSurface,
    surfaceVariant = Bauhaus_md_theme_light_surfaceVariant,
    onSurfaceVariant = Bauhaus_md_theme_light_onSurfaceVariant,
    outline = Bauhaus_md_theme_light_outline,
)

private val BauhausDarkColors = darkColorScheme(
    primary = Bauhaus_md_theme_dark_primary,
    onPrimary = Bauhaus_md_theme_dark_onPrimary,
    primaryContainer = Bauhaus_md_theme_dark_primaryContainer,
    onPrimaryContainer = Bauhaus_md_theme_dark_onPrimaryContainer,
    secondary = Bauhaus_md_theme_dark_secondary,
    onSecondary = Bauhaus_md_theme_dark_onSecondary,
    secondaryContainer = Bauhaus_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Bauhaus_md_theme_dark_onSecondaryContainer,
    tertiary = Bauhaus_md_theme_dark_tertiary,
    onTertiary = Bauhaus_md_theme_dark_onTertiary,
    tertiaryContainer = Bauhaus_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Bauhaus_md_theme_dark_onTertiaryContainer,
    background = Bauhaus_md_theme_dark_background,
    onBackground = Bauhaus_md_theme_dark_onBackground,
    surface = Bauhaus_md_theme_dark_surface,
    onSurface = Bauhaus_md_theme_dark_onSurface,
    surfaceVariant = Bauhaus_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Bauhaus_md_theme_dark_onSurfaceVariant,
    outline = Bauhaus_md_theme_dark_outline,
)

@Composable
fun BauhausAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) BauhausDarkColors else BauhausLightColors,
        content = content,
    )
}
