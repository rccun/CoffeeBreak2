package org.coffeebreak.data.dto

import io.ktor.util.collections.StringMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.coffeebreak.domain.model.UserModel

@Serializable
data class UserModelDto(
    val id: String? = null,
    @SerialName("user_id") val userId: String,
    val name: String,
    val phone: String,
    val address: String? = null,
    val email: String
)

fun UserModelDto.toDomain(): UserModel = (
        UserModel(
            id = id,
            userId = userId,
            name = name,
            phone = phone,
            address = address,
            email = email
        )
        )

