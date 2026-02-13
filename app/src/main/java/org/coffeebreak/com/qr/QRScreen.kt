package org.coffeebreak.com.qr

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.alexzhirkevich.qrose.options.QrBrush
import io.github.alexzhirkevich.qrose.options.QrColors
import io.github.alexzhirkevich.qrose.options.solid
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import org.coffeebreak.com.R
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.bg
import org.coffeebreak.com.theme.blue3

@Composable
fun QRScreen(navController: NavController, viewModel: QRViewModel = hiltViewModel()) {
    val id = viewModel.id.collectAsState().value
    val qrData = remember {
        "coffee://code/$id"
    }
    val qrColor = MainTheme.colorScheme.default
    val bgColor = MainTheme.colorScheme.bg
    val qrColors = remember {
        QrColors(
            dark = QrBrush.solid(qrColor),
            light = QrBrush.solid(bgColor)
        )
    }
    val painter = rememberQrCodePainter(qrData, colors = qrColors)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
    ) {
        Spacer(Modifier.height(21.dp))
        Row {
            MyIcon(R.drawable.back, tintColor = MainTheme.colorScheme.iconBack) {
                navController.popBackStack()
            }
            Spacer(Modifier.weight(1f))
            Text(
                "Профиль",
                color = MainTheme.colorScheme.windowTitle,
                style = MainTheme.typography.authTextField,
                fontSize = 16.sp
            )
            Spacer(Modifier.weight(1f))
            MyIcon(R.drawable.back, tintColor = Color.Transparent) {
                navController.popBackStack()
            }
        }
        Text(
            "Ваш персональный QR-код",
            modifier = Modifier.padding(vertical = 30.dp),
            color = MainTheme.colorScheme.iconBack, fontSize = 20.sp
        )
        Image(
            painter = painter,
            null,
            modifier = Modifier
                .padding(horizontal = 64.dp)
//                .widthIn(min = 247.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(20.dp))
        Text(
            "Покажите ваш QR-code для получения заказа",
            color = blue3,
            fontSize = 18.sp,
            style = MainTheme.typography.authTextField,
            modifier = Modifier.widthIn(max = 236.dp)
        )

    }
}