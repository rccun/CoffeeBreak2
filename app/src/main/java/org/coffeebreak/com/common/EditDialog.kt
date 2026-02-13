package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import org.coffeebreak.com.theme.MainTheme

@Composable
fun EditDialog(onDism: () -> Unit, value: String, onValueChange: (String) -> Unit, onSave: (String) -> Unit, isShow: Boolean) {
    if (isShow) {
        Dialog({ onDism() }) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(MainTheme.colorScheme.default)
                .padding(horizontal = 50.dp)) {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth()
                )
                Row {
                    Text("Отменить", modifier = Modifier
                        .clickable {
                            onDism()
                        }
                        .weight(1f))
                    Text("Сохранить", modifier = Modifier
                        .clickable {
                            onSave(value)
                        }
                        .weight(1f))
                }
            }
        }
    }
}