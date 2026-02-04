package org.coffeebreak.domain.usecases.auth

import org.coffeebreak.domain.utils.EmailValidator

class EmailUseCase(private val repo: EmailValidator) {
    fun execute(email: String) = repo.execute(email)

}