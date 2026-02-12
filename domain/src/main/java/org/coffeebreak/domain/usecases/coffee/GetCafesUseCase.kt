package org.coffeebreak.domain.usecases.coffee

import org.coffeebreak.domain.repository.CoffeeRepository

class GetCafesUseCase(private val repo: CoffeeRepository) {
    suspend fun execute() = repo.getCafes()
}