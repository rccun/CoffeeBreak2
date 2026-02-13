package org.coffeebreak.com.menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecases.coffee.GetCoffeesUseCase
import org.coffeebreak.domain.usecases.user.GetUserUseCase
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getCoffeesUseCase: GetCoffeesUseCase
): ViewModel() {
    private val _state = mutableStateOf(MenuState())
    val state: State<MenuState> = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getUserUseCase.execute()
            val coffees = getCoffeesUseCase.execute()
            if (coffees.isSuccess && res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy (
                        userName = res.getOrNull()!!.name,
                        coffees = coffees.getOrNull()!!,
                        isLoading = false
                    )
                }
            }
        }
    }
}