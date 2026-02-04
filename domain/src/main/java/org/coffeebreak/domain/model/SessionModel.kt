package org.coffeebreak.domain.model

data class SessionModel(
    val id: String,
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: Long
)
