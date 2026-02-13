package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.UserModel

interface UserRepository {
    suspend fun setAddress(address: String): Result<Unit>
    suspend fun getUser(): Result<UserModel>
    suspend fun updateUser(user: UserModel): Result<Unit>
}