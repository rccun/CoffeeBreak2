package org.coffeebreak.domain.usecases.auth

class PasswordUseCase {
    fun execute(password: String): Result<String> {
        if (password.length < 8) {
            return Result.failure(Exception("Пароль должен ыбть не менее 8 символов"))
        } else if (password.lowercase() == password) {
            return Result.failure(Exception("Пароль должен содержать большие буквы"))

        } else if (password.uppercase() == password) {
            return Result.failure(Exception("Пароль должен содержать маленькие буквы"))

        } else if (!password.any { !it.isLetterOrDigit() }) {
            return Result.failure(Exception("Пароль должен содержать спецсимвол"))

        } else if (!password.any { it.isLetter() }) {
            return Result.failure(Exception("Пароль должен содержать цифры"))

        } else if (!password.any { it.isDigit() }) {
            return Result.failure(Exception("Пароль должен содержать буквы"))

        }
        return Result.success("")
    }
}