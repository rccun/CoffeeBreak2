package org.coffeebreak.com.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecases.auth.SignUpUseCase
import org.coffeebreak.domain.usecases.auth.ValidateUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateUseCase: ValidateUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state
    fun onEvent(event: SignUpEvents) {
        when (event) {
            SignUpEvents.OnCheckedChange -> {
                _state.value = _state.value.copy(
                    isChecked = !_state.value.isChecked
                )
            }

            is SignUpEvents.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }

            is SignUpEvents.OnPasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.value
                )
            }

            is SignUpEvents.OnNameChange -> {
                _state.value = _state.value.copy(
                    name = event.value
                )
            }

            is SignUpEvents.OnPhoneChange -> {
                _state.value = _state.value.copy(
                    phone = event.value
                )
            }

            SignUpEvents.OnNextClick -> {
                if (_state.value.email.isNotBlank() &&
                    _state.value.password.isNotBlank() &&
                    _state.value.phone.isNotBlank() &&
                    _state.value.name.isNotBlank()
                ) {
                    val res = validateUseCase.execute(_state.value.email, _state.value.password)
                    if (res.isSuccess) {
                        viewModelScope.launch(Dispatchers.IO) {

                            val res2 =
                                signUpUseCase.execute(_state.value.email, _state.value.password)
                            if (res2.isSuccess) {
                                _state.value = _state.value.copy(
                                    isSuccess = true
                                )
                            } else {
                                _state.value = _state.value.copy(
                                    isError = true,
                                    errorMessage = res.exceptionOrNull()?.message?: "error"
                                )
                            }
                        }
                    } else {
                        _state.value = _state.value.copy(
                            isError = true,
                            errorMessage = res.exceptionOrNull()!!.message!!
                        )
                    }
                } else {
                    _state.value = _state.value.copy(
                        isError = true,
                        errorMessage = "Заполните все поля"
                    )
                }
            }
        }
    }
}