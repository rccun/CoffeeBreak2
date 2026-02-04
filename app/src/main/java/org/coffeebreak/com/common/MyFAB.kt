package org.coffeebreak.com.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.green1

@Composable
fun MyFAB(onClick: () -> Unit) {
    Row {
        Spacer(Modifier.weight(1f))
        FloatingActionButton(
            onClick = {},
            shape = CircleShape,
            containerColor = green1,
            contentColor = MainTheme.colorScheme.bg,
            modifier = Modifier.size(64.dp)
        ) {
            MyIcon(icon = R.drawable.next) {
                onClick()
            }
        }
    }
}