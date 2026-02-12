package org.coffeebreak.com.reset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import org.coffeebreak.com.common.MyDialog
import org.coffeebreak.com.common.MyFAB
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.theme.MainTheme

@Composable
fun ResetScreen(navController: NavController, viewModel: ResetViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.Menu)
        }
    }
    Column(modifier = Modifier.padding(start = 30.dp, top = 26.dp)) {
        MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.authBack) {
            navController.popBackStack()
        }
        Column(modifier = Modifier.padding(start = 10.dp, end = 40.dp)) {
            Spacer(Modifier.height(46.dp))
            Text(
                "Восстановление пароля",
                color = MainTheme.colorScheme.authTitle,
                style = MainTheme.typography.authTitle
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "Введите новый пароль",
                style = MainTheme.typography.authTitle,
                fontSize = 14.sp,
                color = MainTheme.colorScheme.authDesc
            )
            Spacer(Modifier.height(38.dp))
            AuthTextField(
                value = state.password,
                onValueChange = { viewModel.updPassword(it) },
                icon = R.drawable.lock,
                placeholder = "Пароль",
                isPassword = true,
                modifier = Modifier.height(30.dp)
            )
            Spacer(Modifier.weight(1f))
            MyFAB {
                viewModel.setPassword(state.password)
            }
            Spacer(Modifier.weight(2f))
        }
    }
    MyDialog(state.error) {
        viewModel.closeDialog()
    }
}