package com.xiaojinzi.tally.lib.res.ui.theme.champagne

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 香槟金: 暖调香槟与古铜金 + 橄榄点缀, 低调精致

val Champagne_md_theme_light_primary = Color(0xFF8A6A3E)
val Champagne_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val Champagne_md_theme_light_primaryContainer = Color(0xFFFFDEAE)
val Champagne_md_theme_light_onPrimaryContainer = Color(0xFF2C1700)
val Champagne_md_theme_light_secondary = Color(0xFF6F5B40)
val Champagne_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val Champagne_md_theme_light_secondaryContainer = Color(0xFFFBDEBC)
val Champagne_md_theme_light_onSecondaryContainer = Color(0xFF271905)
val Champagne_md_theme_light_tertiary = Color(0xFF506441)
val Champagne_md_theme_light_onTertiary = Color(0xFFFFFFFF)
val Champagne_md_theme_light_tertiaryContainer = Color(0xFFD2EABD)
val Champagne_md_theme_light_onTertiaryContainer = Color(0xFF0E2005)
val Champagne_md_theme_light_background = Color(0xFFFFF8F2)
val Champagne_md_theme_light_onBackground = Color(0xFF1F1B16)
val Champagne_md_theme_light_surface = Color(0xFFFFF8F2)
val Champagne_md_theme_light_onSurface = Color(0xFF1F1B16)
val Champagne_md_theme_light_surfaceVariant = Color(0xFFEFE0CF)
val Champagne_md_theme_light_onSurfaceVariant = Color(0xFF4E4539)
val Champagne_md_theme_light_outline = Color(0xFF807567)

val Champagne_md_theme_dark_primary = Color(0xFFF5C57F)
val Champagne_md_theme_dark_onPrimary = Color(0xFF492B00)
val Champagne_md_theme_dark_primaryContainer = Color(0xFF6A4E22)
val Champagne_md_theme_dark_onPrimaryContainer = Color(0xFFFFDEAE)
val Champagne_md_theme_dark_secondary = Color(0xFFDDC2A1)
val Champagne_md_theme_dark_onSecondary = Color(0xFF3E2D16)
val Champagne_md_theme_dark_secondaryContainer = Color(0xFF56432A)
val Champagne_md_theme_dark_onSecondaryContainer = Color(0xFFFBDEBC)
val Champagne_md_theme_dark_tertiary = Color(0xFFB7CEA3)
val Champagne_md_theme_dark_onTertiary = Color(0xFF243517)
val Champagne_md_theme_dark_tertiaryContainer = Color(0xFF3A4C2B)
val Champagne_md_theme_dark_onTertiaryContainer = Color(0xFFD2EABD)
val Champagne_md_theme_dark_background = Color(0xFF18130D)
val Champagne_md_theme_dark_onBackground = Color(0xFFEAE1D9)
val Champagne_md_theme_dark_surface = Color(0xFF18130D)
val Champagne_md_theme_dark_onSurface = Color(0xFFEAE1D9)
val Champagne_md_theme_dark_surfaceVariant = Color(0xFF4E4539)
val Champagne_md_theme_dark_onSurfaceVariant = Color(0xFFD1C5B4)
val Champagne_md_theme_dark_outline = Color(0xFF9A8F80)

private val ChampagneLightColors = lightColorScheme(
    primary = Champagne_md_theme_light_primary,
    onPrimary = Champagne_md_theme_light_onPrimary,
    primaryContainer = Champagne_md_theme_light_primaryContainer,
    onPrimaryContainer = Champagne_md_theme_light_onPrimaryContainer,
    secondary = Champagne_md_theme_light_secondary,
    onSecondary = Champagne_md_theme_light_onSecondary,
    secondaryContainer = Champagne_md_theme_light_secondaryContainer,
    onSecondaryContainer = Champagne_md_theme_light_onSecondaryContainer,
    tertiary = Champagne_md_theme_light_tertiary,
    onTertiary = Champagne_md_theme_light_onTertiary,
    tertiaryContainer = Champagne_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Champagne_md_theme_light_onTertiaryContainer,
    background = Champagne_md_theme_light_background,
    onBackground = Champagne_md_theme_light_onBackground,
    surface = Champagne_md_theme_light_surface,
    onSurface = Champagne_md_theme_light_onSurface,
    surfaceVariant = Champagne_md_theme_light_surfaceVariant,
    onSurfaceVariant = Champagne_md_theme_light_onSurfaceVariant,
    outline = Champagne_md_theme_light_outline,
)

private val ChampagneDarkColors = darkColorScheme(
    primary = Champagne_md_theme_dark_primary,
    onPrimary = Champagne_md_theme_dark_onPrimary,
    primaryContainer = Champagne_md_theme_dark_primaryContainer,
    onPrimaryContainer = Champagne_md_theme_dark_onPrimaryContainer,
    secondary = Champagne_md_theme_dark_secondary,
    onSecondary = Champagne_md_theme_dark_onSecondary,
    secondaryContainer = Champagne_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Champagne_md_theme_dark_onSecondaryContainer,
    tertiary = Champagne_md_theme_dark_tertiary,
    onTertiary = Champagne_md_theme_dark_onTertiary,
    tertiaryContainer = Champagne_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Champagne_md_theme_dark_onTertiaryContainer,
    background = Champagne_md_theme_dark_background,
    onBackground = Champagne_md_theme_dark_onBackground,
    surface = Champagne_md_theme_dark_surface,
    onSurface = Champagne_md_theme_dark_onSurface,
    surfaceVariant = Champagne_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Champagne_md_theme_dark_onSurfaceVariant,
    outline = Champagne_md_theme_dark_outline,
)

@Composable
fun ChampagneAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) ChampagneDarkColors else ChampagneLightColors,
        content = content,
    )
}
