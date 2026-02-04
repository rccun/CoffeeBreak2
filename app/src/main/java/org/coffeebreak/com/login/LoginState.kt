package org.coffeebreak.com.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)
