package org.coffeebreak.com.startup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.blue3

@Composable
fun StartUpScreen(navController: NavController, viewModel: StartUpViewModel = hiltViewModel()) {
    val isTimeOut = viewModel.isTimeOut.collectAsState().value
    LaunchedEffect(isTimeOut) {
        if (isTimeOut) {
            navController.navigate(Route.CafeMap)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(blue3.copy(alpha = 0.86f))
        ) {
        }
        Image(painter = painterResource(R.drawable.startup), null)
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.weight(3.5f))
            Image(painter = painterResource(R.drawable.cup), null)
            Spacer(Modifier.weight(1f))
            Text(stringResource(R.string.welcome_title), style = MainTheme.typography.splashTitle)
            Spacer(Modifier.weight(4f))

        }

    }
}