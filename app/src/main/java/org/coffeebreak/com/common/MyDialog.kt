package org.coffeebreak.com.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyDialog(text: String, onDism: () -> Unit) {

    AlertDialog(
        { onDism() },
        title = { Text("Ошибка") },
        text = { Text(text) },
        confirmButton = { Text("OK", modifier = Modifier.clickable { onDism() }) })
}