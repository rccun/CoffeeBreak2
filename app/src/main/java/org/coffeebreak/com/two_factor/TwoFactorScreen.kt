package org.coffeebreak.com.two_factor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.common.MyFAB
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.theme.MainTheme

@Composable
fun TwoFactorScreen(navController: NavController, viewModel: TwoFactorViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.Reset)
        }
    }
    Column(modifier = Modifier.padding(start = 30.dp, top = 26.dp)) {
        MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.authBack) {
            navController.popBackStack()
        }
        Column(modifier = Modifier.padding(start = 10.dp, end = 40.dp)) {
            Spacer(Modifier.height(46.dp))
            Text(
                "Проверка",
                color = MainTheme.colorScheme.authTitle,
                style = MainTheme.typography.authTitle
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "Введите код, который мы вам отправили на почту",
                style = MainTheme.typography.authTitle,
                fontSize = 14.sp,
                color = MainTheme.colorScheme.authDesc
            )
            Spacer(Modifier.height(38.dp))
            Row(
            ) {
                Spacer(Modifier.width(18.dp))
                BasicTextField(
                    value = state.otp, onValueChange = { i ->
                        if (i.length <= 4 && i.all { it.isDigit() }) {
                            viewModel.onEvent(TwoFactorEvents.OnDigitEntered(i))
                        }
                        if (i.length == 4) {
                            viewModel.onEvent(TwoFactorEvents.OnEnterEnded)
                        }
                    },
                    cursorBrush = SolidColor(Color.Transparent),
                    textStyle = TextStyle(
                        color = Color.Transparent,
                        fontSize = 1.sp
                    ),
                    decorationBox = { itf ->
                        Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
                            repeat(4) { i ->
                                val char = state.otp.getOrNull(i)?.toString() ?: ""
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            if (char == "") {
                                                MainTheme.colorScheme.authOtp
                                            } else {
                                                MainTheme.colorScheme.activeOtp
                                            }
                                        )
                                        .weight(1f)
                                        .height(61.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = char,
                                        fontSize = 24.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(Modifier.width(18.dp))
            }
            Spacer(Modifier.height(46.dp))
            if (state.isTimeOut) {
                Text(
                    "Выслать код заново",
                    color = MainTheme.colorScheme.authTitle,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            viewModel.timer()
                        }
                )
            } else {
                Text(
                    if (state.isDigit) {
                        "Выслать заново через 00:0${state.timer}"

                    } else {
                        "Выслать заново через 00:${state.timer}"

                    },
                    color = MainTheme.colorScheme.authTime,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(Modifier.height(62.dp))
            MyFAB {
                viewModel.onEvent(TwoFactorEvents.OnEnterEnded)
            }

        }
    }
}