package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.coffeebreak.com.R
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.b3

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    icon: Int,
    placeholder: String,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    val isShow = remember { mutableStateOf(true) }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (isShow.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        textStyle = MainTheme.typography.authTextField,
        decorationBox = {
            Box() {
                Row(
                    modifier = Modifier.align(Alignment.TopCenter),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LeadingIcon(icon)

                    Spacer(Modifier.width(19.dp))
                    if (value.isBlank()) {
                        Text(placeholder, style = MainTheme.typography.authTextField)
                    } else {
                        it.invoke()
                    }
                    Spacer(Modifier.weight(1f))
                    if (isPassword) {
                        MyIcon(icon = R.drawable.show, tintColor = Color.Black) {
                            isShow.value = !isShow.value
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(b3)
                        .align(Alignment.BottomCenter)
                )
            }
        },
        modifier = modifier

    )
}