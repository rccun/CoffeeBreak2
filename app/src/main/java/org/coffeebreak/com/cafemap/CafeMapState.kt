package org.coffeebreak.com.cafemap

import com.yandex.mapkit.geometry.Point
import org.coffeebreak.domain.model.CafeModel

data class CafeMapState(
    val isSuccess: Boolean = false,
    val user: Point? = null,
    val cafes: List<CafeModel> = emptyList()
)
