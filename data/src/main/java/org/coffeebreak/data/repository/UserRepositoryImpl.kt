package org.coffeebreak.data.repository

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.dto.UserModelDto
import org.coffeebreak.data.dto.toDomain
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.repository.UserRepository

class UserRepositoryImpl(
    private val sessionRepository: SessionRepository
): UserRepository {
    override suspend fun setAddress(address: String): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id?: return Result.failure(Exception("lksdjfl"))
        return try {
            val res = client.postgrest["users"]
                .update(
                    buildJsonObject {
                        put("address", address)
                    }
                ) {
                    filter {

                        eq("user_id", userId)
                    }
                }
            Result.success(Unit)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUser(): Result<UserModel> {
        val userId = client.auth.currentUserOrNull()?.id?: return Result.failure(Exception("lksdjfl"))
        return try {
            val res = client.postgrest["users"].select {
                filter {
                    eq("user_id", userId)
                }
            }.decodeSingle<UserModelDto>().toDomain()
            Result.success(res)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateUser(user: UserModel): Result<Unit> {
        val userId = client.auth.currentUserOrNull()?.id?: return Result.failure(Exception("lksdjfl"))

        return try {
            val res = client.postgrest["users"].update(
                buildJsonObject{
                    put("name", user.name)
                    put("phone", user.phone)
                    put("email", user.email)
                    put("address", user.address)
                }
            ) {
                filter {
                    eq("user_id", userId)
                }
            }
            Result.success(Unit)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }
}