package org.coffeebreak.com.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.coffeebreak.com.Route
import org.coffeebreak.com.cafemap.CafeMapScreen
import org.coffeebreak.com.forgot.ForgotScreen
import org.coffeebreak.com.login.LoginScreen
import org.coffeebreak.com.menu.MenuScreen
import org.coffeebreak.com.my_order.MyOrderScreen
import org.coffeebreak.com.profile.ProfileScreen
import org.coffeebreak.com.qr.QRScreen
import org.coffeebreak.com.reset.ResetScreen
import org.coffeebreak.com.reward.RewardScreen
import org.coffeebreak.com.signup.SignUpScreen
import org.coffeebreak.com.startup.StartUpScreen
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.MyCoffeeBreakTheme
import org.coffeebreak.com.two_factor.TwoFactorScreen
import org.coffeebreak.com.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination

            val isAuth = viewModel.isAuth.collectAsState().value
            MyCoffeeBreakTheme {
                Surface(
                    modifier = Modifier.Companion.fillMaxSize(),
                    color = MainTheme.colorScheme.bg
                ) {
                    Scaffold(
                        modifier = Modifier.Companion.fillMaxSize(),
                        containerColor = Color.Companion.Transparent
                    ) { innerPadding ->
                        Box(modifier = Modifier.Companion.padding(innerPadding)) {
                            NavHost(
                                navController,
                                if (isAuth) {
                                    Route.StartUp
                                } else {
                                    Route.Login
                                }
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
                                composable<Route.Menu> {
                                    MenuScreen(navController)
                                }
                                composable<Route.Reward> {
                                    RewardScreen(navController)
                                }
                                composable<Route.MyOrder> {
                                    MyOrderScreen(navController)
                                }
                                composable<Route.Profile> {
                                    ProfileScreen(navController)
                                }
                                composable<Route.QR> {
                                    QRScreen(navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}