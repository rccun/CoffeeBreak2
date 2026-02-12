package org.coffeebreak.domain.usecases.auth

import org.coffeebreak.domain.repository.AuthRepository

class SetPasswordUseCase(private val repo: AuthRepository) {
    suspend fun execute(password: String) = repo.resetPassword(password)
}