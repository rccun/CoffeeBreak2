package org.coffeebreak.data.repository

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.coffeebreak.data.data_source.InitSupabaseClient.client
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
}