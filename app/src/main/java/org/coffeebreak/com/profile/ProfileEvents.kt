package org.coffeebreak.com.profile

interface ProfileEvents {
    data class OnEditClick(val value: String): ProfileEvents
    data object OnCancelClick: ProfileEvents
    data class OnSaveClick(val value: String): ProfileEvents
    data class OnValueChange(val value: String): ProfileEvents
    data object OnExitClick: ProfileEvents
}