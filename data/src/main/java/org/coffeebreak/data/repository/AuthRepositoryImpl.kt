    package org.coffeebreak.data.repository

    import io.github.jan.supabase.gotrue.auth
    import io.github.jan.supabase.gotrue.providers.builtin.Email
    import org.coffeebreak.data.data_source.InitSupabaseClient.client
    import org.coffeebreak.domain.model.SessionModel
    import org.coffeebreak.domain.repository.AuthRepository
    import org.coffeebreak.domain.repository.SessionRepository
    import kotlin.time.ExperimentalTime

    class AuthRepositoryImpl(
        private val sessionRepository: SessionRepository
    ): AuthRepository {
        override suspend fun signIn(
            email: String,
            password: String
        ): Result<Unit> {
            return try {
                val res = client.auth.signInWith(Email) {
                    this.email = email
                    this.password = password
                }
                Result.success(res)
            } catch(e: Exception) {
                Result.failure(e)
            }
        }

        @OptIn(ExperimentalTime::class)
        override suspend fun signUp(
            email: String,
            password: String
        ): Result<Unit> {
            return try {
                client.auth.signUpWith(Email) {
                    this.email = email
                    this.password = password
                }
                val id = client.auth.currentUserOrNull()?.id ?: return Result.failure(Exception("No ID"))
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
            } catch(e: Exception) {
                Result.failure(e)
            }
        }
    }