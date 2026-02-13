package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.blue3
import org.coffeebreak.com.theme.gray3
import org.coffeebreak.com.theme.green1
import org.coffeebreak.com.theme.lightGray

@Composable
fun LoyaltyCard(rate: Int) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(blue3)
            .fillMaxWidth()


    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 14.dp)
        ) {


            Row {
                Text(
                    "Карта лояльности",
                    style = MainTheme.typography.authTextField,
                    color = gray3,
                    fontSize = 14.sp
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "4 / 6",
                    style = MainTheme.typography.authTextField,
                    color = gray3,
                    fontSize = 14.sp
                )
            }
            Spacer(Modifier.height(25.dp))
            Row {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    repeat(6) { i ->
                        MyIcon(
                            R.drawable.cup_l, tintColor = if (i + 1 > rate) {
                                lightGray
                            } else {
                                Color.Unspecified
                            }
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
                Text("16%", color = green1, fontWeight = FontWeight.Medium, fontSize = 25.sp)
            }
            Spacer(Modifier.height(49.dp))
        }
    }
}