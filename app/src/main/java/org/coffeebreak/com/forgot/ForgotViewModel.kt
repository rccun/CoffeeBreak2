package org.coffeebreak.com.forgot

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.coffeebreak.domain.usecases.auth.EmailUseCase
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val emailUseCase: EmailUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ForgotState())
    val state: State<ForgotState> = _state
    fun onEvent(event: ForgotEvents) {
        when (event) {
            is ForgotEvents.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }
            ForgotEvents.OnNextClick -> {
                val res = emailUseCase.execute(_state.value.email)
                if (res.isSuccess) {
                    _state.value = _state.value.copy (
                        isSuccess = true
                    )
                } else {
                    _state.value = _state.value.copy (
                        isError = true,
                        error = res.exceptionOrNull()!!.message!!
                    )
                }
            }
        }
    }
}