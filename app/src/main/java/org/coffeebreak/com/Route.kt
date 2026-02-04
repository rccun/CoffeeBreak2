package org.coffeebreak.com

import kotlinx.serialization.Serializable
import java.io.Serial

@Serializable
sealed class Route() {
    @Serializable data object Welcome: Route()
    @Serializable data object Login: Route()
    @Serializable data object SignUp: Route()
    @Serializable data object CafeMap: Route()
    @Serializable data object StartUp: Route()
}