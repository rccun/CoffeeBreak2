package org.coffeebreak.data.dto

import io.ktor.util.collections.StringMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserModelDto(
    val id: String? = null,
    @SerialName("user_id") val userId: String,
    val name: String,
    val phone: String,
    val address: String? = null
)

