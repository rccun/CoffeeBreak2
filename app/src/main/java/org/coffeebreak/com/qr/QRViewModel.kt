package org.coffeebreak.com.qr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class QRViewModel @Inject constructor(): ViewModel() {
    private val _id = MutableStateFlow("")
    val id = _id.onStart {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")
}