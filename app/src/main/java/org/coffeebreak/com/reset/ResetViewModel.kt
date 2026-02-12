package org.coffeebreak.com.reset

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecases.auth.PasswordUseCase
import org.coffeebreak.domain.usecases.auth.SetPasswordUseCase
import javax.inject.Inject

@HiltViewModel
class ResetViewModel @Inject constructor(
    private val passwordUseCase: PasswordUseCase,
    private val setPasswordUseCase: SetPasswordUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ResetState())
    val state: State<ResetState> = _state

    fun setPassword(password: String) {
        val res = passwordUseCase.execute(password)
        if (res.isSuccess) {
            viewModelScope.launch(Dispatchers.IO) {
                val res2 = setPasswordUseCase.execute(_state.value.password)
//                if (res2.isSuccess) {
                _state.value = _state.value.copy(
                    isSuccess = true
                )
//                } else {
//                    _state.value = _state.value.copy (
//                        isError = true,
//                        error = res2.exceptionOrNull()!!.message!!
//                    )
//                }
            }
        }
    }

    fun updPassword(password: String) {
        _state.value = _state.value.copy(
            password = password
        )
    }

    fun closeDialog() {
        _state.value = _state.value.copy(
            isError = false
        )
    }
}