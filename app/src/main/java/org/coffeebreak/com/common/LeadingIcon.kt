package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.b3

@Composable
fun LeadingIcon(icon: Int) {
    var iconHeight by remember { mutableStateOf(0.dp) }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 3.5.dp)) {
        MyIcon(
            icon = icon,
            tintColor = MainTheme.colorScheme.authLeadingIcon,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .onGloballyPositioned { i ->
                    val height = i.size.height.dp
                    iconHeight = height
                }
        )

        Box(
            modifier = Modifier
                .size(height = iconHeight, width = 1.dp)
                .background(b3)
        )
    }
}