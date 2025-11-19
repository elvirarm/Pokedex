package com.example.pokedexinteractiva.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokedexinteractiva.model.Pokemon
import com.example.pokedexinteractiva.model.Tipo
import com.example.pokedexinteractiva.ui.components.PokemonDialog
import com.example.pokedexinteractiva.ui.components.onPokemonClick

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CabeceraPorTipos(listaPokemon: List<Pokemon>) {

    // rememberSaveable guarda el estado incluso al girar la pantalla.
    // Aquí lo usamos para recordar si el diálogo está abierto.
    var mostrarDialogo by rememberSaveable { mutableStateOf(false) }

    // remember guarda un valor mientras este composable esté en memoria.
    // Aquí sirve para guardar el Pokémon seleccionado al pulsar una card.
    var pokemonSeleccionado by remember { mutableStateOf<Pokemon?>(null) }

    // Obtenemos todas las secciones del enum Tipo y la convertimos a una lista.
    val secciones = Tipo.entries.toList()

    // LazyColumn se usa para mostrar una lista larga sin cargarla entera, solo los elementos visibles + algunos extra para rendimiento.
    LazyColumn {

        secciones.forEach { tipo ->

            // stickyHeader hace que la cabecera del tipo se quede fija arriba mientras se hace scroll dentro de esa sección.
            stickyHeader {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .background(tipo.color.copy(alpha = 0.4f))
                        .padding(8.dp)
                ) {

                    // Texto centrado y en mayúscula.
                    Text(tipo.nombre.uppercase(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )

                }
            }

            // items() recorre únicamente los Pokémon que tengan ese tipo gracias a la función filter.
            items(listaPokemon.filter { it.tipo == tipo }) {

                    pokemon ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                        .clickable {
                            onPokemonClick(
                                pokemon,
                                { pokemonSeleccionado = it },
                                { mostrarDialogo = it }
                            )
                        },

                    //Sombra de la card
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(pokemon.imagen),
                            pokemon.nombre,
                            modifier = Modifier.size(60.dp)
                        )
                        Text(pokemon.nombre)
                        Text(pokemon.tipo.nombre)
                    }

                }
            }
        }

    }

    PokemonDialog(
        mostrarDialogo = mostrarDialogo,
        pokemon = pokemonSeleccionado,
        onCerrar = {
            mostrarDialogo = false
            pokemonSeleccionado = null
        }
    )
}