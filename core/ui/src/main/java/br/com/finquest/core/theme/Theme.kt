package br.com.finquest.core.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue10,
    onPrimary = NeutralWhite10,
    background = PrimaryBlack10,
    onBackground = NeutralWhite10,
    onSurface = NeutralWhite10,
    secondary = PastelPurple10,
    onSecondary = PrimaryBlack10,
    tertiary = PrimaryBlue10
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlue10,
    onPrimary = NeutralWhite10,
    background = NeutralWhite10,
    onBackground = PrimaryBlack10,
    onSurface = PrimaryBlack10,
    secondary = PastelBlue10,
    onSecondary = PrimaryBlack10,
    tertiary = PrimaryBlue10
)

@Composable
fun FinQuestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}