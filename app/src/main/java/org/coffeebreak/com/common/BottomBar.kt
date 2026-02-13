package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.gray3

data class CustomIcon(
    val route: Route,
    val icon: Int

)

val icons = listOf(
    CustomIcon(Route.Menu(), R.drawable.menu),
    CustomIcon(Route.Reward, R.drawable.gift),
    CustomIcon(Route.MyOrder, R.drawable.order),
)

@Composable
fun BottomBar(navController: NavController, currentRoute: Route, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MainTheme.colorScheme.cafeBar)
            .padding(vertical = 21.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        icons.forEach { i ->
            MyIcon(
                icon = i.icon, tintColor = if (i.route == currentRoute) {
                    MainTheme.colorScheme.bottomActiveIcon
                } else {
                    gray3
                }
            ) {
                if (i.route != currentRoute) {
                    navController.navigate(i.route)
                }
            }
        }
    }
}