package org.coffeebreak.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.coffeebreak.com.ui.theme.MainTheme
import org.coffeebreak.com.ui.theme.MyCoffeeBreakTheme
import org.coffeebreak.com.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyCoffeeBreakTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MainTheme.colorScheme.bg) {

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Companion.Transparent
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {

                            NavHost(navController = navController, startDestination = Route.Welcome) {
                                composable<Route.Welcome> {

                                }
                            }
//                            NavHost(
//                                navController = navController,
//                                startDestination = Route.Welcome,
//                            ) {
//                                composable<Route.Welcome> {
//                                    WelcomeScreen(navController)
//                                }
//                            }
                        }
                    }
                }
            }
        }
    }
}