package com.example.schooladmissionsystem.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = AcademicLightBlue,
    secondary = PurpleGrey80,
    tertiary = AcademicGold,
    background = NavyBlue,
    surface = NavyBlueSurface,
    onPrimary = NavyBlue,
    onSecondary = Color.White,
    onTertiary = NavyBlue,
    onBackground = Color.White,
    onSurface = Color.White,
    outline = AcademicLightBlue
)

private val LightColorScheme = lightColorScheme(
    primary = AcademicLightBlue,
    secondary = PurpleGrey40,
    tertiary = AcademicGold,
    background = NavyBlue,
    surface = NavyBlueSurface,
    onPrimary = NavyBlue,
    onSecondary = Color.White,
    onTertiary = NavyBlue,
    onBackground = Color.White,
    onSurface = Color.White,
    outline = AcademicLightBlue
)

@Composable
fun SchooladmissionsystemTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is disabled by default to keep the Navy theme consistent
    dynamicColor: Boolean = false,
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
