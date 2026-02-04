package org.coffeebreak.domain.usecases.auth

class ValidateUseCase(
    private val emailUseCase: EmailUseCase,
    private val passwordUseCase: PasswordUseCase
) {
    fun execute(email: String, password: String): Result<String> {
        val usecase1 = emailUseCase.execute(email)
        if (usecase1.isFailure) {
            return usecase1
        }
        val usecase2 = passwordUseCase.execute(password)
        if (usecase2.isFailure) {
            return usecase2
        }
        return Result.success("")
    }
}