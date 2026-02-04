package org.coffeebreak.data.utils

import android.util.Patterns
import org.coffeebreak.domain.utils.EmailValidator

class EmailValidatorImpl(): EmailValidator {
    override fun execute(email: String): Result<String> {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Result.success("")
        } else {
            return Result.failure(Exception("Введите корректный email"))
        }
    }
}