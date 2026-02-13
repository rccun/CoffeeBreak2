package org.coffeebreak.domain.model

data class UserModel(
    val id: String? = null,
    val userId: String? = null,
    val name: String,
    val phone: String,
    val address: String? = null,
    val email: String
)
