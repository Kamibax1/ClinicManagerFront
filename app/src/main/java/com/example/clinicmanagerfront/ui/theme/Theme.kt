package com.example.clinicmanagerfront.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF007AFF),       // Твой голубой для кнопок и иконок
    secondary = Color(0xFF525F76),     // Спокойный серо-голубой

    // ОСНОВНОЙ ФОН (чуть темнее)
    background = Color(0xFFF0F2F5),    // Мягкий светло-серый
)

@Composable
fun ClinicManagerFrontTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
