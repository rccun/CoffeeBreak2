package org.coffeebreak.domain.repository

interface AuthRepository {
    suspend fun signIn(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String, name: String, phone: String, ): Result<Unit>
    suspend fun resetPassword(password: String): Result<Unit>
}