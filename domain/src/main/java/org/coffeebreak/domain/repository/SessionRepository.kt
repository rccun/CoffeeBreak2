package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.SessionModel

interface SessionRepository {
    suspend fun getSession(): SessionModel?
    suspend fun clearSession()
    suspend fun saveSession(model: SessionModel)
}