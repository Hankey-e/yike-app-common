package com.xiaojinzi.tally.lib.res.ui.theme.amethyst

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 黛紫: 雅致的灰调紫晶 + 柔玫瑰点缀, 低饱和高级感

val Amethyst_md_theme_light_primary = Color(0xFF6C4E96)
val Amethyst_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val Amethyst_md_theme_light_primaryContainer = Color(0xFFEADDFF)
val Amethyst_md_theme_light_onPrimaryContainer = Color(0xFF25005A)
val Amethyst_md_theme_light_secondary = Color(0xFF635B70)
val Amethyst_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val Amethyst_md_theme_light_secondaryContainer = Color(0xFFE9DEF8)
val Amethyst_md_theme_light_onSecondaryContainer = Color(0xFF1F182B)
val Amethyst_md_theme_light_tertiary = Color(0xFF7E5260)
val Amethyst_md_theme_light_onTertiary = Color(0xFFFFFFFF)
val Amethyst_md_theme_light_tertiaryContainer = Color(0xFFFFD9E3)
val Amethyst_md_theme_light_onTertiaryContainer = Color(0xFF31101D)
val Amethyst_md_theme_light_background = Color(0xFFFFFBFF)
val Amethyst_md_theme_light_onBackground = Color(0xFF1C1B1F)
val Amethyst_md_theme_light_surface = Color(0xFFFFFBFF)
val Amethyst_md_theme_light_onSurface = Color(0xFF1C1B1F)
val Amethyst_md_theme_light_surfaceVariant = Color(0xFFE7E0EB)
val Amethyst_md_theme_light_onSurfaceVariant = Color(0xFF49454E)
val Amethyst_md_theme_light_outline = Color(0xFF7A757F)

val Amethyst_md_theme_dark_primary = Color(0xFFD6BAFF)
val Amethyst_md_theme_dark_onPrimary = Color(0xFF3B1B6B)
val Amethyst_md_theme_dark_primaryContainer = Color(0xFF533583)
val Amethyst_md_theme_dark_onPrimaryContainer = Color(0xFFEADDFF)
val Amethyst_md_theme_dark_secondary = Color(0xFFCDC2DB)
val Amethyst_md_theme_dark_onSecondary = Color(0xFF342D40)
val Amethyst_md_theme_dark_secondaryContainer = Color(0xFF4B4358)
val Amethyst_md_theme_dark_onSecondaryContainer = Color(0xFFE9DEF8)
val Amethyst_md_theme_dark_tertiary = Color(0xFFEFB8C8)
val Amethyst_md_theme_dark_onTertiary = Color(0xFF4A2532)
val Amethyst_md_theme_dark_tertiaryContainer = Color(0xFF633B48)
val Amethyst_md_theme_dark_onTertiaryContainer = Color(0xFFFFD9E3)
val Amethyst_md_theme_dark_background = Color(0xFF1C1B1F)
val Amethyst_md_theme_dark_onBackground = Color(0xFFE5E1E6)
val Amethyst_md_theme_dark_surface = Color(0xFF1C1B1F)
val Amethyst_md_theme_dark_onSurface = Color(0xFFE5E1E6)
val Amethyst_md_theme_dark_surfaceVariant = Color(0xFF49454E)
val Amethyst_md_theme_dark_onSurfaceVariant = Color(0xFFCAC4CF)
val Amethyst_md_theme_dark_outline = Color(0xFF948F99)

private val AmethystLightColors = lightColorScheme(
    primary = Amethyst_md_theme_light_primary,
    onPrimary = Amethyst_md_theme_light_onPrimary,
    primaryContainer = Amethyst_md_theme_light_primaryContainer,
    onPrimaryContainer = Amethyst_md_theme_light_onPrimaryContainer,
    secondary = Amethyst_md_theme_light_secondary,
    onSecondary = Amethyst_md_theme_light_onSecondary,
    secondaryContainer = Amethyst_md_theme_light_secondaryContainer,
    onSecondaryContainer = Amethyst_md_theme_light_onSecondaryContainer,
    tertiary = Amethyst_md_theme_light_tertiary,
    onTertiary = Amethyst_md_theme_light_onTertiary,
    tertiaryContainer = Amethyst_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Amethyst_md_theme_light_onTertiaryContainer,
    background = Amethyst_md_theme_light_background,
    onBackground = Amethyst_md_theme_light_onBackground,
    surface = Amethyst_md_theme_light_surface,
    onSurface = Amethyst_md_theme_light_onSurface,
    surfaceVariant = Amethyst_md_theme_light_surfaceVariant,
    onSurfaceVariant = Amethyst_md_theme_light_onSurfaceVariant,
    outline = Amethyst_md_theme_light_outline,
)

private val AmethystDarkColors = darkColorScheme(
    primary = Amethyst_md_theme_dark_primary,
    onPrimary = Amethyst_md_theme_dark_onPrimary,
    primaryContainer = Amethyst_md_theme_dark_primaryContainer,
    onPrimaryContainer = Amethyst_md_theme_dark_onPrimaryContainer,
    secondary = Amethyst_md_theme_dark_secondary,
    onSecondary = Amethyst_md_theme_dark_onSecondary,
    secondaryContainer = Amethyst_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Amethyst_md_theme_dark_onSecondaryContainer,
    tertiary = Amethyst_md_theme_dark_tertiary,
    onTertiary = Amethyst_md_theme_dark_onTertiary,
    tertiaryContainer = Amethyst_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Amethyst_md_theme_dark_onTertiaryContainer,
    background = Amethyst_md_theme_dark_background,
    onBackground = Amethyst_md_theme_dark_onBackground,
    surface = Amethyst_md_theme_dark_surface,
    onSurface = Amethyst_md_theme_dark_onSurface,
    surfaceVariant = Amethyst_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Amethyst_md_theme_dark_onSurfaceVariant,
    outline = Amethyst_md_theme_dark_outline,
)

@Composable
fun AmethystAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) AmethystDarkColors else AmethystLightColors,
        content = content,
    )
}
