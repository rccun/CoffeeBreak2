package org.coffeebreak.data.repository

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.coffeebreak.data.utils.SecureStorage
import org.coffeebreak.domain.model.SessionModel

class SecureStorageImpl(context: Context): SecureStorage {
    private val prefs = EncryptedSharedPreferences.create(
        context,
        "secure_storage",
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    override suspend fun loadSession(): SessionModel? {
        val userId = prefs.getString("user_id", null)?: return null
        return SessionModel(
            id = userId,
            accessToken = prefs.getString("access_token", "")?: "",
            refreshToken = prefs.getString("refresh_token", "")?: "",
            expiredAt = prefs.getLong("expires_at", 0L),
        )
    }

    override suspend fun clearSession() {
        prefs.edit().clear().apply()
    }

    override suspend fun saveSession(model: SessionModel) {
        prefs.edit()
            .putString("user_id", model.id)
            .putString("access_token", model.accessToken)
            .putString("refresh_token", model.refreshToken)
            .putLong("expires_at", model.expiredAt)
            .apply()
    }
}