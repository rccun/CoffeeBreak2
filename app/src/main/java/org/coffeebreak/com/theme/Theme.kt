package org.coffeebreak.com.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class CustomColorTheme(
    val bg: Color = Color.Unspecified,
    val default: Color = Color.Unspecified,
    val splashBox: Color = Color.Unspecified,
    val splashDesc: Color = Color.Unspecified,
    val authLeadingIcon: Color = Color.Unspecified,
    val authBack: Color = Color.Unspecified,
    val authTitle: Color = Color.Unspecified,
    val authDesc: Color = Color.Unspecified,
    val authText: Color = Color.Unspecified,
    val authOtp: Color = Color.Unspecified,
    val authTime: Color = Color.Unspecified,
    val activeOtp: Color = Color.Unspecified,
    val cafeBar: Color = Color.Unspecified,

)

val LightColorScheme = CustomColorTheme(
    bg = bgW,
    default = Color.Black,
    splashBox = bgW.copy(alpha = 0.2f),
    splashDesc = darkBlue,
    authLeadingIcon = green2,
    authBack = Color.Black,
    authTitle = green1,
    authDesc = blue3,
    authText = green2,
    authOtp = gray1,
    authTime = blue3,
    activeOtp = active,
    cafeBar = bgW
)
val DarkColorScheme = CustomColorTheme(
    bg = bgB,
    default = bgW,
    splashBox = blue3.copy(alpha = 0.2f),
    splashDesc = bgW,
    authLeadingIcon = b1,
    authBack = b1,
    authTitle = b1,
    authDesc = b3,
    authText = blue3,
    authOtp = gray2,
    authTime = lightGray.copy(alpha = 0.5f),
    activeOtp = lightBlue,
    cafeBar = navMenu
)

val LocalColorProvider = staticCompositionLocalOf { CustomColorTheme() }

@Composable
fun MyCoffeeBreakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val theme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }


    CompositionLocalProvider(
        LocalColorProvider provides theme,
        content = content
    )
}

object MainTheme {
    val colorScheme: CustomColorTheme
        @Composable
        get() = LocalColorProvider.current
    val typography: MyTypo
        @Composable @ReadOnlyComposable
        get() = Typography

}