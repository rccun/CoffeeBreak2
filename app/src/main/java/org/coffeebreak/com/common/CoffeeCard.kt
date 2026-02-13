package org.coffeebreak.com.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import io.ktor.util.collections.StringMap
import org.coffeebreak.com.theme.bgW

@Composable
fun CoffeeCard(
    imageUrl: String, title: String,
    coast: Int, onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(bgW)
            .fillMaxWidth()
            .heightIn(min = 154.dp)
            .clickable {
                onClick()
            }
            .padding(7.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyAsync(
                imageUrl, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
            )
            Spacer(Modifier.height(15.dp))
            Text(title)
        }
        Text("$coast P", modifier = Modifier.align(Alignment.BottomEnd))

    }
}