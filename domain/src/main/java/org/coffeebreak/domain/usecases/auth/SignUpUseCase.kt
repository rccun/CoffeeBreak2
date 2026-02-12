package org.coffeebreak.domain.usecases.auth

import org.coffeebreak.domain.repository.AuthRepository

class SignUpUseCase(private val repo: AuthRepository) {
    suspend fun execute(email: String, password: String, name: String, phone: String, ) = repo.signUp(email, password, name, phone)
}