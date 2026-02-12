package org.coffeebreak.com.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.coffeebreak.com.R

data class MyTypo(
    val splashTitle: TextStyle = TextStyle.Default,
    val splashDesc: TextStyle = TextStyle.Default,
    val splashDesc2: TextStyle = TextStyle.Default,
    val authTextField: TextStyle = TextStyle.Default,
    val authTitle: TextStyle = TextStyle.Default,
    val addressText: TextStyle = TextStyle.Default,
)
val fontR = FontFamily(
    Font(R.font.r_r, FontWeight.Normal)
)
val fontP = FontFamily(
    Font(R.font.poppins_m, FontWeight.Medium)
)
val Typography = MyTypo(
    splashTitle = TextStyle(
        fontFamily = fontR,
        fontWeight = FontWeight.Normal,
        fontSize = 64.sp,
        color = bgW
    ),
    splashDesc = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.Medium,
        fontSize = 25.sp
    ),
    splashDesc2 = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    authTextField = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = b3
    ),
    authTitle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    addressText = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
)