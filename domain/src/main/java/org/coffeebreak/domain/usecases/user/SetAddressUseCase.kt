package org.coffeebreak.domain.usecases.user

import org.coffeebreak.domain.repository.UserRepository

class SetAddressUseCase(private val repo: UserRepository) {
    suspend fun execute(address: String) = repo.setAddress(address)
}