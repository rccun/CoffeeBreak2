package org.coffeebreak.com.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.jan.supabase.compose.auth.composable.GoogleDialogType
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.common.AuthTextField
import org.coffeebreak.com.common.MyFAB
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.lightGray
import org.coffeebreak.data.data_source.InitSupabaseClient.client

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.CafeMap)
        }
    }
    val res = client.composeAuth.rememberSignInWithGoogle(
        type = GoogleDialogType.BOTTOM_SHEET,
        onResult = {
            when (it) {
                NativeSignInResult.ClosedByUser -> {
                    Log.e("TAG", "Closed by user");
                }
                is NativeSignInResult.Error -> {
                    Log.e("TAG", "Error");

                }
                is NativeSignInResult.NetworkError -> {
                    Log.e("TAG", "Network error");

                }

                NativeSignInResult.Success -> {
                    Log.e("TAG", "Success");
                    navController.navigate(Route.StartUp)
                }
            }
        }
    )
//    Column(modifier = Modifier.padding(start = 30.dp, top = 26.dp)) {
//        MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.authBack) {
//            navController.popBackStack()
//        }
    Column(modifier = Modifier.padding(horizontal = 40.dp)) {
        Spacer(Modifier.weight(1f))
        Text(
            "Войти",
            color = MainTheme.colorScheme.authTitle,
            style = MainTheme.typography.authTitle
        )
        Spacer(Modifier.height(24.dp))
        Text(
            "Добро пожаловать",
            style = MainTheme.typography.authTitle,
            fontSize = 14.sp,
            color = MainTheme.colorScheme.authDesc
        )
        Spacer(Modifier.weight(0.62f))
        AuthTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(LoginEvents.OnEmailChange(it)) },
            icon = R.drawable.email_icon,
            placeholder = "Адрес электронной почты",
            modifier = Modifier.height(30.dp)

        )
        Spacer(Modifier.height(36.dp))
        AuthTextField(
            value = state.password,
            onValueChange = { viewModel.onEvent(LoginEvents.OnPasswordChange(it)) },
            icon = R.drawable.lock,
            placeholder = "Пароль",
            isPassword = true,
            modifier = Modifier.height(30.dp)
        )
        Spacer(Modifier.height(27.dp))

        Text(
            "Забыли пароль?", modifier = Modifier.align(Alignment.CenterHorizontally).clickable{
                navController.navigate(Route.Forgot)
            },
            style = MainTheme.typography.authTitle,
            fontSize = 14.sp,
            color = MainTheme.colorScheme.authText
        )
        Spacer(Modifier.weight(1.37f))
        MyFAB {
            viewModel.onEvent(LoginEvents.OnNextClick)
        }
        Spacer(Modifier.height(19.dp))
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                "Войти с помощью",
                color = MainTheme.colorScheme.default,
                style = MainTheme.typography.authTitle,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.height(40.dp)
            ) {
                Image(painter = painterResource(R.drawable.yandex), null)
                Image(painter = painterResource(R.drawable.google), null, modifier = Modifier.clickable{
                    res.startFlow()
                })
                Image(painter = painterResource(R.drawable.vk), null)
            }
        }
        Spacer(Modifier.height(36.dp))
        Row {
            Text(
                "Впервые? ",
                style = MainTheme.typography.authTitle,
                fontSize = 14.sp,
                color = lightGray
            )
            Text(
                "Зарегистрироваться",
                style = MainTheme.typography.authTitle,
                fontSize = 14.sp,
                color = MainTheme.colorScheme.authTitle,
                modifier = Modifier.clickable{
                    navController.navigate(Route.SignUp)
                }
            )
        }
        Spacer(Modifier.weight(0.9f))
    }
}
//}

@Composable
fun Google(navController: NavController) {
    val res = client.composeAuth.rememberSignInWithGoogle(
        onResult = {
            when (it) {
                NativeSignInResult.ClosedByUser -> {
                    Log.e("TAG", "Closed by user");
                }
                is NativeSignInResult.Error -> {
                    Log.e("TAG", "Error");

                }
                is NativeSignInResult.NetworkError -> {
                    Log.e("TAG", "Network error");

                }

                NativeSignInResult.Success -> {
                    Log.e("TAG", "Success");
                    navController.navigate(Route.StartUp)
                }
            }
        }
    ) {

    }
}