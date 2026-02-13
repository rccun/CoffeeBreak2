package org.coffeebreak.com.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.com.R
import org.coffeebreak.com.Route
import org.coffeebreak.com.common.EditDialog
import org.coffeebreak.com.common.MyIcon
import org.coffeebreak.com.common.ProfileItem
import org.coffeebreak.com.theme.MainTheme
import org.coffeebreak.domain.model.UserModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {

    val state = viewModel.state.value
    val loading = state.isLoading
    Column(modifier = Modifier.padding(horizontal = 25.dp)) {

        Spacer(Modifier.height(21.dp))
        Row {
            MyIcon(R.drawable.back, tintColor = MainTheme.colorScheme.iconBack) {
                navController.popBackStack()
                viewModel.onEvent(ProfileEvents.OnExitClick)

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
        Spacer(Modifier.height(29.dp))
        if (loading) {
            Text("Downloading", color = MainTheme.colorScheme.default)
        } else {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {


                ProfileItem(R.drawable.name, "Имя", state.name, R.drawable.edit) {
                    viewModel.onEvent(ProfileEvents.OnEditClick("Имя"))
                }
                ProfileItem(
                    R.drawable.phone,
                    "Phone number",
                    state.phone,
                    R.drawable.edit
                ) {
                    viewModel.onEvent(ProfileEvents.OnEditClick("phone"))
                }
                ProfileItem(
                    R.drawable.email_icon,
                    "Email",
                    state.email,
                    R.drawable.edit
                ) {
                    viewModel.onEvent(ProfileEvents.OnEditClick("email"))
                }
                ProfileItem(
                    R.drawable.location,
                    "Адрес кофейни Magic cafe",
                    state.address,
                    R.drawable.edit
                ) {
                    viewModel.onEvent(ProfileEvents.OnEditClick("address"))
                }
                ProfileItem(
                    R.drawable.qr_icon,
                    "QR-code",
                    "Для получения заказа",
                    R.drawable.next2
                ) {
                    navController.navigate(Route.QR)
                    viewModel.onEvent(ProfileEvents.OnExitClick)
                }
            }
        }
    }
    EditDialog(
        onDism = {
        viewModel.onEvent(ProfileEvents.OnCancelClick)
    },
        value = state.editingItem, onValueChange = {
            viewModel.onEvent(ProfileEvents.OnValueChange(it))
        },
        onSave = {
            viewModel.onEvent(ProfileEvents.OnSaveClick(state.editingItem))
        }, state.isEditorShow
    )
}