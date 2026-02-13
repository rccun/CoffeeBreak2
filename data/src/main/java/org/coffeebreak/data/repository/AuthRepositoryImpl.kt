package org.coffeebreak.data.repository

import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.postgrest.postgrest
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.dto.UserModelDto
import org.coffeebreak.domain.model.SessionModel
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.repository.SessionRepository
import kotlin.time.ExperimentalTime

class AuthRepositoryImpl(
    private val sessionRepository: SessionRepository
) : AuthRepository {
    @OptIn(ExperimentalTime::class)
    override suspend fun signIn(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            val res = client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val id =
                client.auth.currentUserOrNull()?.id ?: return Result.failure(Exception("No ID"))
            val userInfo = client.auth.currentSessionOrNull()
            userInfo?.let {
                sessionRepository.saveSession(
                    SessionModel(
                        id = id,
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiredAt = it.expiresAt.epochSeconds
                    )
                )
            }
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun resetPassword(password: String): Result<Unit> {
        return try {
//            client.auth.
            val res = client.auth.updateUser {
                this.password = password
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        phone: String,
    ): Result<Unit> {
        return try {
            client.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }?.let {
                client.postgrest["users"].insert(
                    UserModelDto(
                        userId = it.id,
                        name = name,
                        phone = phone,
                        email = email
                    )
//                    mapOf(
//                        "user_id" to it.id,
//                        "name" to name,
//                        "phone" to phone,
//
//                    )
                )
            }
            val id =
                client.auth.currentUserOrNull()?.id ?: return Result.failure(Exception("No ID"))
            val userInfo = client.auth.currentSessionOrNull()
            userInfo?.let {
                sessionRepository.saveSession(
                    SessionModel(
                        id = id,
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiredAt = it.expiresAt.epochSeconds
                    )
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}