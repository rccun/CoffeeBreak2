package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.blue3
import org.coffeebreak.com.theme.gray3

@Composable
fun PointsCard() {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(blue3)
        .fillMaxWidth()

    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 25.dp, horizontal = 20.dp)) {
            Spacer(Modifier.width(10.dp))
            Column {
                Text("Мои баллы:", color = gray3, fontSize = 20.sp)
                Spacer(Modifier.height(5.dp))
                Text("240", color = MainTheme.colorScheme.menuTitle, fontSize = 25.sp)
            }
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0x19A2CDE9))
            ) {
                Text("Оплатить баллами", modifier = Modifier.padding(7.dp), color = gray3)
            }
        }
        MyIcon(R.drawable.beans, modifier = Modifier.align(Alignment.BottomEnd))
    }
}