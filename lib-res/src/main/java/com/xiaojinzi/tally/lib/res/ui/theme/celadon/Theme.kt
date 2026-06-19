package com.xiaojinzi.tally.lib.res.ui.theme.celadon

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 晨雾青瓷: 中式青瓷绿 + 雾蓝点缀, 温润内敛

val Celadon_md_theme_light_primary = Color(0xFF3A6A60)
val Celadon_md_theme_light_onPrimary = Color(0xFFFFFFFF)
val Celadon_md_theme_light_primaryContainer = Color(0xFFBCECDF)
val Celadon_md_theme_light_onPrimaryContainer = Color(0xFF00201A)
val Celadon_md_theme_light_secondary = Color(0xFF4A635C)
val Celadon_md_theme_light_onSecondary = Color(0xFFFFFFFF)
val Celadon_md_theme_light_secondaryContainer = Color(0xFFCCE8DE)
val Celadon_md_theme_light_onSecondaryContainer = Color(0xFF06201A)
val Celadon_md_theme_light_tertiary = Color(0xFF436277)
val Celadon_md_theme_light_onTertiary = Color(0xFFFFFFFF)
val Celadon_md_theme_light_tertiaryContainer = Color(0xFFC8E6FF)
val Celadon_md_theme_light_onTertiaryContainer = Color(0xFF001E2E)
val Celadon_md_theme_light_background = Color(0xFFFBFDF9)
val Celadon_md_theme_light_onBackground = Color(0xFF191C1B)
val Celadon_md_theme_light_surface = Color(0xFFFBFDF9)
val Celadon_md_theme_light_onSurface = Color(0xFF191C1B)
val Celadon_md_theme_light_surfaceVariant = Color(0xFFDBE5DF)
val Celadon_md_theme_light_onSurfaceVariant = Color(0xFF3F4945)
val Celadon_md_theme_light_outline = Color(0xFF6F7975)

val Celadon_md_theme_dark_primary = Color(0xFFA1D0C3)
val Celadon_md_theme_dark_onPrimary = Color(0xFF023730)
val Celadon_md_theme_dark_primaryContainer = Color(0xFF214E46)
val Celadon_md_theme_dark_onPrimaryContainer = Color(0xFFBCECDF)
val Celadon_md_theme_dark_secondary = Color(0xFFB1CCC2)
val Celadon_md_theme_dark_onSecondary = Color(0xFF1C352F)
val Celadon_md_theme_dark_secondaryContainer = Color(0xFF334B45)
val Celadon_md_theme_dark_onSecondaryContainer = Color(0xFFCCE8DE)
val Celadon_md_theme_dark_tertiary = Color(0xFFAACBE3)
val Celadon_md_theme_dark_onTertiary = Color(0xFF103447)
val Celadon_md_theme_dark_tertiaryContainer = Color(0xFF2A4B5E)
val Celadon_md_theme_dark_onTertiaryContainer = Color(0xFFC8E6FF)
val Celadon_md_theme_dark_background = Color(0xFF101413)
val Celadon_md_theme_dark_onBackground = Color(0xFFE1E3E0)
val Celadon_md_theme_dark_surface = Color(0xFF101413)
val Celadon_md_theme_dark_onSurface = Color(0xFFE1E3E0)
val Celadon_md_theme_dark_surfaceVariant = Color(0xFF3F4945)
val Celadon_md_theme_dark_onSurfaceVariant = Color(0xFFBFC9C3)
val Celadon_md_theme_dark_outline = Color(0xFF89938E)

private val CeladonLightColors = lightColorScheme(
    primary = Celadon_md_theme_light_primary,
    onPrimary = Celadon_md_theme_light_onPrimary,
    primaryContainer = Celadon_md_theme_light_primaryContainer,
    onPrimaryContainer = Celadon_md_theme_light_onPrimaryContainer,
    secondary = Celadon_md_theme_light_secondary,
    onSecondary = Celadon_md_theme_light_onSecondary,
    secondaryContainer = Celadon_md_theme_light_secondaryContainer,
    onSecondaryContainer = Celadon_md_theme_light_onSecondaryContainer,
    tertiary = Celadon_md_theme_light_tertiary,
    onTertiary = Celadon_md_theme_light_onTertiary,
    tertiaryContainer = Celadon_md_theme_light_tertiaryContainer,
    onTertiaryContainer = Celadon_md_theme_light_onTertiaryContainer,
    background = Celadon_md_theme_light_background,
    onBackground = Celadon_md_theme_light_onBackground,
    surface = Celadon_md_theme_light_surface,
    onSurface = Celadon_md_theme_light_onSurface,
    surfaceVariant = Celadon_md_theme_light_surfaceVariant,
    onSurfaceVariant = Celadon_md_theme_light_onSurfaceVariant,
    outline = Celadon_md_theme_light_outline,
)

private val CeladonDarkColors = darkColorScheme(
    primary = Celadon_md_theme_dark_primary,
    onPrimary = Celadon_md_theme_dark_onPrimary,
    primaryContainer = Celadon_md_theme_dark_primaryContainer,
    onPrimaryContainer = Celadon_md_theme_dark_onPrimaryContainer,
    secondary = Celadon_md_theme_dark_secondary,
    onSecondary = Celadon_md_theme_dark_onSecondary,
    secondaryContainer = Celadon_md_theme_dark_secondaryContainer,
    onSecondaryContainer = Celadon_md_theme_dark_onSecondaryContainer,
    tertiary = Celadon_md_theme_dark_tertiary,
    onTertiary = Celadon_md_theme_dark_onTertiary,
    tertiaryContainer = Celadon_md_theme_dark_tertiaryContainer,
    onTertiaryContainer = Celadon_md_theme_dark_onTertiaryContainer,
    background = Celadon_md_theme_dark_background,
    onBackground = Celadon_md_theme_dark_onBackground,
    surface = Celadon_md_theme_dark_surface,
    onSurface = Celadon_md_theme_dark_onSurface,
    surfaceVariant = Celadon_md_theme_dark_surfaceVariant,
    onSurfaceVariant = Celadon_md_theme_dark_onSurfaceVariant,
    outline = Celadon_md_theme_dark_outline,
)

@Composable
fun CeladonAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (useDarkTheme) CeladonDarkColors else CeladonLightColors,
        content = content,
    )
}
