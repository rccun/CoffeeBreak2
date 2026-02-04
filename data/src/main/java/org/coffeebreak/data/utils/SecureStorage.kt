package org.coffeebreak.data.utils

import org.coffeebreak.domain.model.SessionModel

interface SecureStorage {
    suspend fun loadSession(): SessionModel?
    suspend fun clearSession()
    suspend fun saveSession(model: SessionModel)
}