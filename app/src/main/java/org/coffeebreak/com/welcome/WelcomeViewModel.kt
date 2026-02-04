package org.coffeebreak.com.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(): ViewModel() {
    private val _isTimeOut = MutableStateFlow<Boolean>(false)
    val isTimeOut = _isTimeOut.onStart {
        delay(5000)
        _isTimeOut.update { true }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
}