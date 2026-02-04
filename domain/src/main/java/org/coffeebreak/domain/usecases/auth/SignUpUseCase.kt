package org.coffeebreak.domain.usecases.auth

import org.coffeebreak.domain.repository.AuthRepository

class SignUpUseCase(private val repo: AuthRepository) {
    suspend fun execute(email: String, password: String) = repo.signUp(email, password)
}