package org.coffeebreak.domain.repository

interface UserRepository {
    suspend fun setAddress(address: String): Result<Unit>
}