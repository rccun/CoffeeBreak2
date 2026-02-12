package org.coffeebreak.com.two_factor

data class TwoFactorState(
    val isSuccess: Boolean = false,
    val otp: String = "",
    val timer: Int = 30,
    val isTimeOut: Boolean = false,
    val isDigit: Boolean = false,
)
