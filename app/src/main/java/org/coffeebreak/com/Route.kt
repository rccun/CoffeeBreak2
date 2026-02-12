package org.coffeebreak.com

import kotlinx.serialization.Serializable

@Serializable
sealed class Route() {
    @Serializable data object Welcome: Route()
    @Serializable data object Login: Route()
    @Serializable data object SignUp: Route()
    @Serializable data object CafeMap: Route()
    @Serializable data object StartUp: Route()
    @Serializable data object TwoFactor: Route()
    @Serializable data object Reset: Route()
    @Serializable data object Forgot: Route()
    @Serializable data class Menu(val id: String? = null): Route()
}