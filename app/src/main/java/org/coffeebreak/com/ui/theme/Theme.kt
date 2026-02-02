package org.coffeebreak.com.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

data class CustomColorTheme(
    val bg: Color = Color.Unspecified,
)

private val DarkColorScheme = CustomColorTheme(
    bg = bgB
)
private val LightColorScheme = CustomColorTheme(
    bg = bgW
)


val LocalCustomThemeProvider = staticCompositionLocalOf { CustomColorTheme() }

@Composable
fun MyCoffeeBreakTheme(
    isSystemDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val scheme = if (isSystemDark) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
    CompositionLocalProvider(
        LocalCustomThemeProvider provides scheme,
        content = content
    )
}

object MainTheme {
    val colorScheme: CustomColorTheme
        @Composable
        get() = LocalCustomThemeProvider.current
    val typography: MyTypography
        @Composable
        get() = Typography
}
