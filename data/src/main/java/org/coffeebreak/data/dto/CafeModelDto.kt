package org.coffeebreak.data.dto

import io.ktor.util.collections.StringMap
import kotlinx.serialization.Serializable
import org.coffeebreak.domain.model.CafeModel

@Serializable
data class CafeModelDto(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val address: String
)

fun CafeModelDto.toDomain(): CafeModel = (
        CafeModel(
            id = id,
            latitude = latitude,
            longitude = longitude,
            address = address
        )
        )
