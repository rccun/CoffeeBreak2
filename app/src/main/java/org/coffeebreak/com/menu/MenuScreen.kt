package org.coffeebreak.com.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.common.BottomBar
import org.coffeebreak.com.common.CoffeeCard
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.data.dto.CoffeeModelDto

@Composable
fun MenuScreen(navController: NavController, viewModel: MenuViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Box(/*modifier = Modifier.padding(bottom = 18.dp)*/) {

        Column() {
            Spacer(Modifier.height(27.dp))
            Row(modifier = Modifier.padding(horizontal = 25.dp)) {
                Column {
                    Text(
                        "Добро пожаловать!",
                        style = MainTheme.typography.addressText,
                        color = MainTheme.colorScheme.menuTitle
                    )
                    Text(
                        state.userName,
                        style = MainTheme.typography.authTextField,
                        fontSize = 18.sp,
                        color = MainTheme.colorScheme.menuName
                    )
                }
                Spacer(Modifier.weight(1f))
                MyIcon(R.drawable.cart, tintColor = MainTheme.colorScheme.icon) {
                    navController.navigate(Route.Reward)
                }
                Spacer(Modifier.width(20.dp))
                MyIcon(R.drawable.profile, tintColor = MainTheme.colorScheme.icon) {
                    navController.navigate(Route.Profile)
                }

            }
            Spacer(Modifier.height(7.dp))
            if (state.isLoading) {
                Text("Downloading")
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp)
                        )
                        .background(MainTheme.colorScheme.menuBg)
                ) {
                    Spacer(Modifier.height(16.dp))

                    Text(
                        "Выберите ваш кофе", modifier = Modifier.padding(start = 25.dp),
                        style = MainTheme.typography.authTextField,
                        fontSize = 18.sp,
                        color = MainTheme.colorScheme.menuTitle2
                    )
                    Spacer(Modifier.height(29.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(17.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(horizontal = 25.dp)
                    ) {
                        items(state.coffees) { i ->
                            CoffeeCard(i.imageUrl, i.title, i.coast) {
                                navController.navigate(Route.CreateOrder)
                            }
                        }
                    }
                    Spacer(Modifier.weight(1f))
                }
            }
        }
        if (!state.isLoading) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 25.dp)
            ) {

                BottomBar(
                    navController,
                    Route.Menu()
                )
                Spacer(Modifier.height(18.dp))
            }
        }

    }
}