package org.coffeebreak.com.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.common.MyFAB
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.bgW
import org.coffeebreak.com.theme.green1
import org.coffeebreak.com.theme.lightGray

@Composable
fun WelcomeScreen(navController: NavController, viewModel: WelcomeViewModel = hiltViewModel()) {
    val isTimeOut = viewModel.isTimeOut.collectAsState().value
    LaunchedEffect(isTimeOut) {
        if (isTimeOut) {
            navController.navigate(Route.Login)
        }
    }
    Column {
        Spacer(Modifier.weight(0.19f))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(green1)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.cup), null)
            Text(stringResource(R.string.welcome_title), style = MainTheme.typography.splashTitle)
        }
        Column(
            modifier = Modifier
                .weight(1.21f)
                .padding(horizontal = 50.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Spacer(Modifier.weight(1f))
                Column(modifier = Modifier.weight(4f)) {
                    Spacer(Modifier.height(25.dp))
                    Text(
                        stringResource(R.string.splash_desc),
                        style = MainTheme.typography.splashDesc,
                        color = MainTheme.colorScheme.splashDesc,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        stringResource(R.string.splash_desc2),
                        style = MainTheme.typography.splashDesc2,
                        color = lightGray,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(51.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(3) { i ->
                    if (i == 0) {

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(green1)
                                .size(height = 10.dp, width = 33.dp)
                        )
                    } else {

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MainTheme.colorScheme.splashBox)
                                .size(10.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            MyFAB {
                navController.navigate(Route.Login)
            }
            Spacer(Modifier.weight(1f))
        }
    }
}