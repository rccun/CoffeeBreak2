package org.coffeebreak.com.reward

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.coffeebreak.com.common.LoyaltyCard
import org.coffeebreak.com.common.PointsCard
import org.coffeebreak.com.theme.MainTheme

@Composable
fun RewardScreen(navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 25.dp)) {
        Spacer(Modifier.height(21.dp))
        Text(
            "Вознаграждение",
            color = MainTheme.colorScheme.windowTitle,
            style = MainTheme.typography.authTextField,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(31.dp))

        LoyaltyCard(5)
        Spacer(Modifier.height(24.dp))
        PointsCard()
    }
}