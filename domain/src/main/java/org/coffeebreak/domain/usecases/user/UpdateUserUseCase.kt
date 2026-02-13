package org.coffeebreak.domain.usecases.user

import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.repository.UserRepository

class UpdateUserUseCase(private val repo: UserRepository) {
    suspend fun execute(user: UserModel) = repo.updateUser(user)
}