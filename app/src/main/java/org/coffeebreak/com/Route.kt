package org.coffeebreak.com

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Route() {
    @Serializable @SerialName("sdlf") data object Welcome: Route()
}