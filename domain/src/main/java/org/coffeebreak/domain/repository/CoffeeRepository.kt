package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.CafeModel

interface CoffeeRepository {
    suspend fun getCafes(): Result<List<CafeModel>>
}