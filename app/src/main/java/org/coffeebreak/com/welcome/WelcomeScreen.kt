package org.coffeebreak.com.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.ui.theme.bgB
import org.coffeebreak.com.ui.theme.green1

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(modifier = Modifier.background(green1)) {


        MyIcon(icon = R.drawable.welcome_cup, tintColor = bgB) { }

    }
}