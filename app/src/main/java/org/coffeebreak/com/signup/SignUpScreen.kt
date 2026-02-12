package org.coffeebreak.com.signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.common.AuthTextField
import org.coffeebreak.com.common.MyFAB
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.com.theme.blue3
import org.coffeebreak.com.theme.lightGray

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.CafeMap)
        }
    }
    Column(modifier = Modifier.padding(start = 30.dp, top = 26.dp)) {
        MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.authBack) {
            navController.popBackStack()
        }
        Column(modifier = Modifier.padding(start = 10.dp, end = 40.dp)) {
            Spacer(Modifier.weight(1f))
            Text(
                "Зарегистрироваться",
                color = MainTheme.colorScheme.authTitle,
                style = MainTheme.typography.authTitle
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "Создать аккаунт здесь",
                style = MainTheme.typography.authTitle,
                fontSize = 14.sp,
                color = MainTheme.colorScheme.authDesc
            )
            Spacer(Modifier.weight(0.62f))
            AuthTextField(
                value = state.name,
                onValueChange = { viewModel.onEvent(SignUpEvents.OnNameChange(it)) },
                icon = R.drawable.name,
                placeholder = "Имя пользователя",
                modifier = Modifier.height(30.dp)

            )
            Spacer(Modifier.height(36.dp))
            AuthTextField(
                value = state.phone,
                onValueChange = { viewModel.onEvent(SignUpEvents.OnPhoneChange(it)) },
                icon = R.drawable.phone,
                placeholder = "Номер мобильного телефона",
                modifier = Modifier.height(30.dp)

            )
            Spacer(Modifier.height(36.dp))
            AuthTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(SignUpEvents.OnEmailChange(it)) },
                icon = R.drawable.email_icon,
                placeholder = "Адрес электронной почты",
                modifier = Modifier.height(30.dp)

            )
            Spacer(Modifier.height(36.dp))
            AuthTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(SignUpEvents.OnPasswordChange(it)) },
                icon = R.drawable.lock,
                placeholder = "Пароль",
                isPassword = true,
                modifier = Modifier.height(30.dp)
            )
            Spacer(Modifier.height(26.dp))
            Row {
                Checkbox(state.isChecked, { viewModel.onEvent(SignUpEvents.OnCheckedChange) })
                Spacer(Modifier.width(8.dp))
                Text(
                    "Я принимаю условия пользовательского соглашения",
                    style = MainTheme.typography.authTitle,
                    fontSize = 14.sp,
                    color = blue3
                )
            }
            Spacer(Modifier.height(27.dp))
            MyFAB {
                viewModel.onEvent(SignUpEvents.OnNextClick)
            }
            Spacer(Modifier.weight(2f))
            Row {
                Text(
                    "Уже зарегистрировались? ",
                    style = MainTheme.typography.authTitle,
                    fontSize = 14.sp,
                    color = lightGray
                )
                Text(
                    "Войти",
                    style = MainTheme.typography.authTitle,
                    fontSize = 14.sp,
                    color = MainTheme.colorScheme.authTitle,
                    modifier = Modifier.clickable {
                        navController.navigate(Route.Login)
                    }
                )
            }
            Spacer(Modifier.weight(1.8f))
        }
    }
}