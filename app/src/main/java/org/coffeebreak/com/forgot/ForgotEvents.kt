package org.coffeebreak.com.forgot

interface ForgotEvents {
    data class OnEmailChange(val value: String): ForgotEvents
    data object OnNextClick: ForgotEvents
}