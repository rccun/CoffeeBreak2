package org.coffeebreak.com.forgot

data class ForgotState(
    val email: String = "",
    val error: String = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false,

)
