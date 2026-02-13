package org.coffeebreak.com.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.usecases.user.GetUserUseCase
import org.coffeebreak.domain.usecases.user.UpdateUserUseCase
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getUserUseCase.execute()
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        name = res.getOrNull()!!.name,
                        phone = res.getOrNull()!!.phone,
                        email = res.getOrNull()!!.email,
                        address = res.getOrNull()!!.address ?: "",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun onEvent(event: ProfileEvents) {
        when (event) {
            is ProfileEvents.OnEditClick -> {
                _state.value = _state.value.copy(
                    isEditorShow = true,
                    editingItemTitle = event.value,
                    editingItem = when (event.value) {
                        "phone" -> {
                            _state.value.phone
                        }

                        "address" -> {
                            _state.value.address
                        }

                        "email" -> {
                            _state.value.email
                        }

                        else -> {
                            _state.value.name
                        }
                    }
                )
            }

            ProfileEvents.OnCancelClick -> {
                _state.value = _state.value.copy(
                    isEditorShow = false,
                    editingItem = "",
                    editingItemTitle = ""
                )
            }

            is ProfileEvents.OnSaveClick -> {
                when (_state.value.editingItemTitle) {
                    "phone" -> {
                        _state.value = _state.value.copy(
                            phone = event.value
                        )
                    }

                    "address" -> {
                        _state.value = _state.value.copy(
                            address = event.value
                        )
                    }

                    "email" -> {
                        _state.value = _state.value.copy(
                            email = event.value
                        )
                    }

                    else -> {
                        _state.value = _state.value.copy(
                            name = event.value
                        )
                    }
                }
                _state.value = _state.value.copy(
                    isEditorShow = false,
                    editingItem = "",
                    editingItemTitle = ""
                )
            }

            is ProfileEvents.OnValueChange -> {
                _state.value = _state.value.copy(
                    editingItem = event.value
                )
            }
            ProfileEvents.OnExitClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val res = updateUserUseCase.execute(
                        UserModel(
                            name = _state.value.name,
                            phone = _state.value.phone,
                            address = _state.value.address,
                            email = _state.value.email,
                        )
                    )
                }
            }
        }
    }
}