package com.example.pokedexinteractiva.data
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.pokedexinteractiva.model.Rol
import com.example.pokedexinteractiva.model.Sesion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "sesion_pokedex")

class SesionStore(private val context: Context) {

    private val KEY_LOGUEADO = booleanPreferencesKey("logueado")
    private val KEY_EMAIL = stringPreferencesKey("email")
    private val KEY_ROL = stringPreferencesKey("rol")

    val sesionFlow: Flow<Sesion> = context.dataStore.data.map { prefs ->
        val logueado = prefs[KEY_LOGUEADO] ?: false
        val email = prefs[KEY_EMAIL]
        val rol = prefs[KEY_ROL]?.let { Rol.valueOf(it) }

        Sesion(
            logueado = logueado,
            email = email,
            rol = rol
        )
    }

    suspend fun guardarSesion(sesion: Sesion) {
        context.dataStore.edit { prefs ->
            prefs[KEY_LOGUEADO] = sesion.logueado

            sesion.email?.let { prefs[KEY_EMAIL] = it } ?: prefs.remove(KEY_EMAIL)
            sesion.rol?.let { prefs[KEY_ROL] = it.name } ?: prefs.remove(KEY_ROL)
        }
    }

    suspend fun borrarSesion() {
        context.dataStore.edit { it.clear() }
    }
}
