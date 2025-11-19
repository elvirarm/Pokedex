package com.example.pokedexinteractiva.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedexinteractiva.model.Pokemon
import com.example.pokedexinteractiva.ui.components.PokemonDialog
import com.example.pokedexinteractiva.ui.components.onPokemonClick

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonGrid(listaPokemon: List<Pokemon>) {

    // rememberSaveable guarda el estado incluso si giramos la pantalla.
    // Aquí lo usamos para recordar si el diálogo está abierto o no.
    var mostrarDialogo by rememberSaveable { mutableStateOf(false) }

    // remember mantiene un valor mientras este composable esté en memoria.
    // Guardamos qué Pokémon se ha pulsado en el grid.
    var pokemonSeleccionado by remember { mutableStateOf<Pokemon?>(null) }

    LazyVerticalGrid(
        //GridCells.Fixed(3) indica que queremos 3 columnas fijas.
        columns = GridCells.Fixed(3)
    ) {
        // items() recorre la lista de Pokémon y muestra uno por celda del grid.
        items(listaPokemon) { pokemon ->

            Card(
                modifier = Modifier
                    // Borde negro alrededor de cada tarjeta
                    .border(BorderStroke(1.dp, Color.Black))

                    // clickable permite detectar pulsaciones sobre la tarjeta
                    .clickable {
                        onPokemonClick(
                            pokemon,
                            { pokemonSeleccionado = it },
                            { mostrarDialogo = it }
                        )
                    },
                // Color de fondo de cada card, basado en el tipo del Pokémon
                colors = CardDefaults.cardColors(
                    containerColor = pokemon.tipo.color.copy(alpha = 0.4f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(pokemon.imagen),
                        contentDescription = pokemon.nombre,
                        modifier = Modifier.size(60.dp)

                    )

                    Text(pokemon.nombre)
                    Text(pokemon.tipo.nombre)
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