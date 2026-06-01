package com.example.clinicmanagerfront.data.repository
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit { putString(KEY_JWT_TOKEN, token) }
    }

    fun getToken(): String? {
        return prefs.getString(KEY_JWT_TOKEN, null)
    }

    fun clearAll() {
        prefs.edit { clear() }
    }

    fun clearToken() {
        prefs.edit { remove(KEY_JWT_TOKEN) }
    }

    companion object {
        private const val KEY_JWT_TOKEN = "jwt_token"
    }

}
