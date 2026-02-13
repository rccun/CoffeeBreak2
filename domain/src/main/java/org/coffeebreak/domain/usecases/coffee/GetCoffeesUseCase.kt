package org.coffeebreak.domain.usecases.coffee

import org.coffeebreak.domain.repository.CoffeeRepository

class GetCoffeesUseCase(private val repo: CoffeeRepository) {
    suspend fun execute() = repo.getCoffees()
}