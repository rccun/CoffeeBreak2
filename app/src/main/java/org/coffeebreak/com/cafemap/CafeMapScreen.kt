package org.coffeebreak.com.cafemap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import org.coffeebreak.com.R
import org.coffeebreak.com.common.MyFAB
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.bgW
import org.coffeebreak.com.theme.blue3
import org.coffeebreak.com.theme.green1

@Composable
fun CafeMapScreen(navController: NavController, viewModel: CafeMapViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val isPermission = rememberSaveable { mutableStateOf(false) }
    val mapObjects = remember { mapView.map.mapObjects.addCollection() }

    DisposableEffect(Unit) {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        onDispose {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }
    if (!isPermission.value) {
        GetPermission() {
            isPermission.value = true
        }
    }
    LaunchedEffect(isPermission.value) {
        if (isPermission.value) {
            getUserLocation(context) {
                viewModel.updateUser(it)
            }
        }
    }
    LaunchedEffect(state.user) {
        state.user?.let {
            val placemark = mapObjects.addPlacemark(it)
            placemark.setIcon(
                ImageProvider.fromResource(context, R.drawable.user_point)
            )
            moveHome(mapView, it)
        }
    }
    if (state.cafes.isNotEmpty()) {
        mapObjects.clear()
        state.cafes.forEach { i ->
            val placemark = mapObjects.addPlacemark(Point(i.latitude, i.longitude))
            placemark.setIcon(ImageProvider.fromResource(context, R.drawable.point))
        }
    }
    Box() {

        AndroidView(factory = { mapView }, modifier = Modifier.fillMaxSize())
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {

            Box(
                modifier = Modifier
                    .padding(bottom = 35.dp, end = 30.dp)
                    .align(Alignment.End),
            ) {
                FloatingActionButton(
                    onClick = {},
                    shape = CircleShape,
                    containerColor = blue3,
                    contentColor = MainTheme.colorScheme.bg,
                    modifier = Modifier
                        .size(64.dp),
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp,
                        focusedElevation = 0.dp,
                        hoveredElevation = 0.dp
                    )
                ) {
                    MyIcon(icon = R.drawable.go_home, tintColor = bgW) {
                        state.user?.let {
                            moveHome(mapView, it)
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                    .background(green1)
            ) {
                Text(
                    "Выберите кофейню Coffee Break",
                    color = bgW,
                    modifier = Modifier
                        .padding(vertical = 27.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MainTheme.typography.authTextField,
                    fontSize = 16.sp
                )
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                        .background(
                            MainTheme.colorScheme.cafeBar
                        )
                        .fillMaxWidth()
                ) {
                    if (state.cafes.isEmpty()) {
                        Text("Downloading")
                    } else {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 30.dp, vertical = 21.dp),
                            verticalArrangement = Arrangement.spacedBy(7.dp)
                        ) {

                            state.cafes.forEach { i ->
                                Row(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(green1)
                                        .padding(13.dp)
                                        .clickable {
                                            viewModel.saveAddress(i.address)
                                        },
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    MyIcon(R.drawable.address, tintColor = bgW)
                                    Spacer(Modifier.width(11.dp))
                                    Text(
                                        i.address,
                                        color = bgW,
                                        style = MainTheme.typography.addressText
                                    )
                                    Spacer(Modifier.weight(1f))
                                    MyIcon(R.drawable.next2, tintColor = bgW)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun moveHome(mapView: MapView, point: Point) {
    mapView.map.move(
        CameraPosition(point, 16f, 0f, 0f),
        Animation(Animation.Type.SMOOTH, 1f), null
    )
}

@SuppressLint("MissingPermission")
fun getUserLocation(context: Context, action: (Point) -> Unit) {
    val t = LocationServices.getFusedLocationProviderClient(context)
    t.getCurrentLocation(
        com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
        null

    ).addOnSuccessListener { i ->

        if (i != null) {
            action(Point(i.latitude, i.longitude))
        }
    }
}

@Composable
fun GetPermission(action: () -> Unit) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                action()
            } else {
                openSettings(context)
            }
        }
    LaunchedEffect(Unit) {
        launcher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
}

fun openSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("pacakage", context.packageName, null)
    )
    intent.addFlags(
        Intent.FLAG_ACTIVITY_NEW_TASK
    )
    context.startActivity(intent)
}