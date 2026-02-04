package org.coffeebreak.com.signup

interface SignUpEvents {
    data class OnEmailChange(val value: String): SignUpEvents
    data class OnPasswordChange(val value: String): SignUpEvents
    data class OnPhoneChange(val value: String): SignUpEvents
    data class OnNameChange(val value: String): SignUpEvents
data object OnCheckedChange: SignUpEvents
data object OnNextClick: SignUpEvents
}