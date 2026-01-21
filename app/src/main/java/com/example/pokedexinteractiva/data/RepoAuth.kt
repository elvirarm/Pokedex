package com.example.pokedexinteractiva.data

import com.example.pokedexinteractiva.data.db.UsuarioDao
import com.example.pokedexinteractiva.data.db.UsuarioEntity
import com.example.pokedexinteractiva.model.Rol
import com.example.pokedexinteractiva.model.Sesion

class RepoAuth(private val dao: UsuarioDao) {

    // Crea usuarios demo si la tabla está vacía
    suspend fun asegurarUsuariosDemo() {
        if (dao.count() == 0) {
            dao.insert(UsuarioEntity("user@demo.com", "123456", Rol.USUARIO.name))
            dao.insert(UsuarioEntity("admin@demo.com", "admin123", Rol.ADMINISTRADOR.name))
        }
    }

    // Login consultando Room
    suspend fun login(email: String, password: String): Result<Sesion> {
        val e = email.trim().lowercase()

        val usuario = dao.getByEmail(e)
            ?: return Result.failure(Exception("Usuario no encontrado"))

        if (usuario.password != password) {
            return Result.failure(Exception("Contraseña incorrecta"))
        }

        val rol = Rol.valueOf(usuario.rol)

        return Result.success(
            Sesion(
                logueado = true,
                email = usuario.email,
                rol = rol
            )
        )
    }

    suspend fun registrar(email: String, password: String, rol: Rol = Rol.USUARIO): Result<Unit> {
        val e = email.trim().lowercase()
        if (dao.getByEmail(e) != null) return Result.failure(Exception("Ese email ya existe"))
        dao.insert(UsuarioEntity(e, password, rol.name))
        return Result.success(Unit)
    }
}
