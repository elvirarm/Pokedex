package com.example.pokedexinteractiva.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexinteractiva.data.RepoAuth
import com.example.pokedexinteractiva.data.SesionStore
import com.example.pokedexinteractiva.model.Rol
import com.example.pokedexinteractiva.model.Sesion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
    
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val errorEmail: String? = null,
    val errorPassword: String? = null,
    val errorGeneral: String? = null,
    val cargando: Boolean = false,
    val sesion: Sesion = Sesion(),
    val rolSeleccionado: Rol? = Rol.USUARIO
)

class AuthViewModel(
    private val repo: RepoAuth,
    private val store: SesionStore
) : ViewModel() {

    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui.asStateFlow()

    init {
        viewModelScope.launch {
            store.sesionFlow.collect { sesionGuardada ->
                _ui.update { it.copy(sesion = sesionGuardada) }
            }
        }
    }

    fun onEmailChange(nuevo: String) {
        _ui.update { it.copy(email = nuevo, errorEmail = null, errorGeneral = null) }
    }

    fun onPasswordChange(nuevo: String) {
        _ui.update { it.copy(password = nuevo, errorPassword = null, errorGeneral = null) }
    }

    fun onRolChange(nuevoRol: Rol) {
        _ui.update { it.copy(rolSeleccionado = nuevoRol, errorGeneral = null) }
    }

    fun registrar(onSuccess: () -> Unit) {
        val email = _ui.value.email.trim()
        val pass = _ui.value.password
        val rol = _ui.value.rolSeleccionado ?: Rol.USUARIO

        if (email.isBlank()) {
            _ui.update { it.copy(errorGeneral = "El email no puede estar vacío") }
            return
        }

        if (pass.length < 6) {
            _ui.update { it.copy(errorGeneral = "La contraseña debe tener al menos 6 caracteres") }
            return
        }

        viewModelScope.launch {
            val result = repo.registrar(email, pass, rol)

            result.onSuccess {
                _ui.update { state -> state.copy(errorGeneral = null) }
                onSuccess()
            }.onFailure { e ->
                _ui.update { state ->
                    state.copy(errorGeneral = e.message ?: "Error al registrar usuario")
                }
            }
        }
    }

    private fun validar(email: String, password: String): Boolean {
        val emailTrim = email.trim()

        val errorEmail = when {
            emailTrim.isBlank() -> "El email no puede estar vacío"
            !emailTrim.contains("@") -> "Email no válido"
            else -> null
        }

        val errorPassword = when {
            password.isBlank() -> "La contraseña no puede estar vacía"
            password.length < 6 -> "La contraseña debe tener al menos 6 caracteres"
            else -> null
        }

        _ui.update { it.copy(errorEmail = errorEmail, errorPassword = errorPassword) }

        return errorEmail == null && errorPassword == null
    }

    fun login() {
        val email = _ui.value.email
        val pass = _ui.value.password

        if (!validar(email, pass)) return

        _ui.update { it.copy(cargando = true, errorGeneral = null) }

        viewModelScope.launch {
            repo.asegurarUsuariosDemo()

            val result = repo.login(email.trim(), pass)

            result.onSuccess { sesion ->
                store.guardarSesion(sesion)
                _ui.update { it.copy(cargando = false) }
            }.onFailure { e ->
                _ui.update {
                    it.copy(
                        cargando = false,
                        errorGeneral = e.message ?: "Error desconocido"
                    )
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            store.borrarSesion()
            _ui.update {
                it.copy(
                    email = "",
                    password = "",
                    errorEmail = null,
                    errorPassword = null,
                    errorGeneral = null,
                    cargando = false,
                    sesion = Sesion()
                )
            }
        }
    }

    fun esAdmin(): Boolean {
        return _ui.value.sesion.logueado && _ui.value.sesion.rol == Rol.ADMINISTRADOR
    }
}
