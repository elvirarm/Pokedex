package com.example.pokedexinteractiva.model

data class Sesion(
    val logueado: Boolean = false,
    val email: String? = null,
    val rol: Rol? = null
)
