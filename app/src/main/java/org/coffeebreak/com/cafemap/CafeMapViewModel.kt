package org.coffeebreak.com.cafemap

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yandex.mapkit.geometry.Point
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecases.coffee.GetCafesUseCase
import org.coffeebreak.domain.usecases.user.SetAddressUseCase
import javax.inject.Inject

@HiltViewModel
class CafeMapViewModel @Inject constructor(
    private val getCafesUseCase: GetCafesUseCase,
    private val setAddressUseCase: SetAddressUseCase
): ViewModel() {
    private val _state = mutableStateOf(CafeMapState())
    val state: State<CafeMapState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCafesUseCase.execute()
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy (
                        cafes = res.getOrNull()!!
                    )
                }
            }
        }
    }
    fun updateUser(point: Point) {
        _state.value = _state.value.copy (
            user = point
        )
    }
    fun saveAddress(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = setAddressUseCase.execute(address)
            if (res.isSuccess) {
                _state.value = _state.value.copy (
                    isSuccess = true
                )
            }
        }
    }
}