package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yandex.mapkit.geometry.Circle
import org.coffeebreak.com.R
import org.coffeebreak.com.theme.MainTheme

@Composable
fun ProfileItem(icon: Int, title: String, text: String, endIcon: Int, onClick: () -> Unit = {}) {
    Column {

        Row(
            modifier = Modifier.clickable {
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MainTheme.colorScheme.profileBox)
                    .sizeIn(minWidth = 42.dp)
            ) {
                MyIcon(
                    icon = icon,
                    tintColor = MainTheme.colorScheme.bottomActiveIcon,
                    modifier = Modifier.padding(13.dp)
                )
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(title, color = MainTheme.colorScheme.icon)
                Spacer(Modifier.height(4.dp))
                Text(
                    text,
                    color = MainTheme.colorScheme.profileText,
                    style = MainTheme.typography.addressText
                )
            }
            Spacer(Modifier.weight(1f))
            MyIcon(endIcon, tintColor = MainTheme.colorScheme.bottomActiveIcon)
        }
        Spacer(Modifier.height(26.dp))
    }

}