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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.coffeebreak.com.cafemap.CafeMapScreen
import org.coffeebreak.com.forgot.ForgotScreen
import org.coffeebreak.com.login.LoginScreen
import org.coffeebreak.com.menu.MenuScreen
import org.coffeebreak.com.reset.ResetScreen
import org.coffeebreak.com.signup.SignUpScreen
import org.coffeebreak.com.startup.StartUpScreen
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.MyCoffeeBreakTheme
import org.coffeebreak.com.two_factor.TwoFactorScreen
import org.coffeebreak.com.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyCoffeeBreakTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MainTheme.colorScheme.bg
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            NavHost(
                                navController, Route.Login
                            ) {
                                composable<Route.Welcome> {
                                    WelcomeScreen(navController)
                                }
                                composable<Route.Login> {
                                    LoginScreen(navController)
                                }
                                composable<Route.StartUp> {
                                    StartUpScreen(navController)
                                }
                                composable<Route.SignUp> {
                                    SignUpScreen(navController)
                                }
                                composable<Route.TwoFactor> {
                                    TwoFactorScreen(navController)
                                }
                                composable<Route.Reset> {
                                    ResetScreen(navController)
                                }
                                composable<Route.Forgot> {
                                    ForgotScreen(navController)
                                }
                                composable<Route.Menu> {
                                    MenuScreen(navController)
                                }
                                composable<Route.CafeMap> {
                                    CafeMapScreen(navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
