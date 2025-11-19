package com.example.pokedexinteractiva.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedexinteractiva.model.Pokemon

// Composable reutilizable que muestra un diálogo con la información de un Pokémon.
@Composable
fun PokemonDialog(
    mostrarDialogo: Boolean,   // Indica si el diálogo debe mostrarse
    pokemon: Pokemon?,         // Pokémon seleccionado (puede ser null si aún no hay uno seleccionado)
    onCerrar: () -> Unit       // Función que se ejecuta al cerrar el diálogo
) {

    // Solo mostramos el AlertDialog si mostrarDialogo es true y el Pokémon no es null
    if (mostrarDialogo && pokemon != null) {

        AlertDialog(
            // onDismissRequest se ejecuta cuando el usuario toca fuera del diálogo o pulsa atrás
            onDismissRequest = { onCerrar() },

            // Imagen del Pokémon, mostrada arriba del diálogo
            icon = {
                Image(
                    painter = painterResource(pokemon.imagen),
                    contentDescription = pokemon.nombre,
                    modifier = Modifier.size(100.dp)
                )
            },

            // Título del diálogo, mostrando el nombre del Pokémon
            title = { Text("Detalles de ${pokemon.nombre}") },

            // Contenido principal del diálogo: nombre, tipo y descripción
            text = {
                Column {
                    Text("Nombre: ${pokemon.nombre}")
                    Text("Tipo: ${pokemon.tipo.nombre}")
                    Text("Descripción: ${pokemon.descripcion}")
                }
            },

            // Botón de confirmación (único botón), que cierra el diálogo al pulsarse
            confirmButton = {
                TextButton(onClick = { onCerrar() }) {
                    Text("Cerrar")
                }
            }
        )
    }
}
