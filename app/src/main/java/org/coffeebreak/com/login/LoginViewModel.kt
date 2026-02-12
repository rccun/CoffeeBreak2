package org.coffeebreak.com.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecases.auth.SignInUseCase
import org.coffeebreak.domain.usecases.auth.ValidateUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val validateUseCase: ValidateUseCase,
): ViewModel() {
    private  val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.OnEmailChange -> {
                _state.value = _state.value.copy (
                    email = event.value
                )
            }
            is LoginEvents.OnPasswordChange -> {
                _state.value = _state.value.copy (
                    password = event.value
                )
            }
            LoginEvents.OnNextClick -> {
                if (_state.value.email.isNotBlank() && _state.value.password.isNotBlank()) {

                    val res = validateUseCase.execute(_state.value.email, _state.value.password)
                    if (res.isSuccess) {
                        viewModelScope.launch(Dispatchers.IO) {

                            val res2 = signInUseCase.execute(_state.value.email, _state.value.password)
                            if (res2.isSuccess) {
                                _state.value = _state.value.copy (
                                    isSuccess = true
                                )
                            } else {
                                _state.value = _state.value.copy (
                                    isError = true,
                                    errorMessage = res.exceptionOrNull()?.message?: "Unknown error"
                                )
                            }
                        }
                    } else {
                        _state.value = _state.value.copy (
                            isError = true,
                            errorMessage = res.exceptionOrNull()!!.message!!
                        )
                    }
                } else {
                    _state.value = _state.value.copy (
                        isError = true,
                        errorMessage = "Заполните все поля"
                    )
                }
            }
        }
    }
}