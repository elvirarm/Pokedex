package com.example.pokedexinteractiva.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey val email: String,
    val password: String,
    val rol: String // "USUARIO" o "ADMINISTRADOR"
)
