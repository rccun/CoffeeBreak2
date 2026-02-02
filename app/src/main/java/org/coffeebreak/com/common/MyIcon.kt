package org.coffeebreak.com.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController

@Composable
fun MyIcon(icon: Int, tintColor: Color? = null, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val t =  LocalContentColor.current
    Icon(
        imageVector = ImageVector.vectorResource(icon),
        contentDescription = null,
        tint = (tintColor ?: t) ,
        modifier = modifier.clickable{
            onClick()
        }
    )
}