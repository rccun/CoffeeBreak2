package org.coffeebreak.com.signup

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val name: String = "",
    val isChecked: Boolean = false,

    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)