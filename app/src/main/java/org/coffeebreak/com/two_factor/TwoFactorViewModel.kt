package org.coffeebreak.com.two_factor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.concurrent.timer

@HiltViewModel
class TwoFactorViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(TwoFactorState())
    val state: State<TwoFactorState> = _state

    init {
        timer()
    }

    fun timer() {
        _state.value = _state.value.copy (
            timer = 30,
            isTimeOut = false
        )
        viewModelScope.launch(Dispatchers.IO) {
            for (i in 0..30) {
                delay(1000)
                withContext(Dispatchers.Main) {
                    if (_state.value.timer > 1) {
                        if (_state.value.timer < 11) {
                            _state.value = _state.value.copy (
                                isDigit = true
                            )
                        }
                        _state.value = _state.value.copy(
                            timer = _state.value.timer - 1
                        )
                    } else {
                        _state.value = _state.value.copy(
                            isTimeOut = true
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: TwoFactorEvents) {
        when (event) {
            is TwoFactorEvents.OnDigitEntered -> {
                _state.value = _state.value.copy(
                    otp = event.value
                )
            }

            TwoFactorEvents.OnEnterEnded -> {
                _state.value = _state.value.copy(
                    isSuccess = true
                )
            }
        }
    }
}