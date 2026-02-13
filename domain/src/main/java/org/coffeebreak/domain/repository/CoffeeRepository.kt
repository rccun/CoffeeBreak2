package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.CafeModel
import org.coffeebreak.domain.model.CoffeeModel

interface CoffeeRepository {
    suspend fun getCafes(): Result<List<CafeModel>>
    suspend fun getCoffees(): Result<List<CoffeeModel>>
}