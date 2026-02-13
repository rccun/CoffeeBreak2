package org.coffeebreak.com.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecases.session.GetSessionUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getSessionUseCase: GetSessionUseCase
): ViewModel() {
    private val _isAuth = MutableStateFlow(false)
    val isAuth = _isAuth.onStart {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getSessionUseCase.execute()
            _isAuth.update {
                res != null
            }

        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false
    )
}