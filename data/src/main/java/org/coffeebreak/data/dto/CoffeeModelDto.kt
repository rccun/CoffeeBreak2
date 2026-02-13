package org.coffeebreak.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.coffeebreak.domain.model.CoffeeModel

@Serializable
data class CoffeeModelDto(

    val id: String,
    @SerialName("image_url") val imageUrl: String,
    val title: String,
    val coast: Int
)
fun CoffeeModelDto.toDomain(): CoffeeModel = (
        CoffeeModel(
            id = id,
            title = title,
            imageUrl = imageUrl,
            coast = coast
        )
        )