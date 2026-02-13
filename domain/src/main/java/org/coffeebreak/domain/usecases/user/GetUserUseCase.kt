package org.coffeebreak.domain.usecases.user

import org.coffeebreak.domain.repository.UserRepository

class GetUserUseCase(private val repo: UserRepository) {
    suspend fun execute() = repo.getUser()

}