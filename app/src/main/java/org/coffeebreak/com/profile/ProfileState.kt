package org.coffeebreak.com.profile

data class ProfileState(
    val isEditorShow: Boolean = false,
    val editingItem: String = "",
    val editingItemTitle: String = "",
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val isLoading: Boolean = true
)
