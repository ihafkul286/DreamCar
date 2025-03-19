package com.example.dreamcar.data


import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import java.io.IOException

// Extensión para crear el DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

// Modelo de preferencias del usuario
data class UserPreferences(
    val username: String = "Usuario",
    val darkMode: String = "system"
)

// Repositorio para manejar DataStore
class UserPreferencesRepository(context: Context) {
    private val dataStore = context.dataStore

    // Claves para guardar los valores en DataStore
    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val DARK_MODE_KEY = stringPreferencesKey("dark_mode")
    }

    // Es un flujo que obtiene los valores guardados en DataStore
    val userPrefs: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                username = preferences[USERNAME_KEY] ?: "Usuario",
                darkMode = preferences[DARK_MODE_KEY] ?: "system"
            )
        }

    // Función para guardar las preferencias del usuario en DataStore
    suspend fun savePreferences(userPrefs: UserPreferences) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = userPrefs.username
            preferences[DARK_MODE_KEY] = userPrefs.darkMode
        }
    }
}
