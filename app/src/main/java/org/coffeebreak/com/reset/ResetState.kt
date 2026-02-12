package org.coffeebreak.com.reset

data class ResetState(
    val password: String = "",
    val error: String = "",
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
)
