package com.example.pokedexinteractiva.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonList(listaPokemon: List<Pokemon>){

    var mostrarDialogo by rememberSaveable { mutableStateOf(false) }

    // remember mantiene un valor mientras este composable esté en memoria.
    // Guardamos qué Pokémon se ha pulsado en el grid.
    var pokemonSeleccionado by remember { mutableStateOf<Pokemon?>(null) }

    // LazyColumn se usa para mostrar una lista vertical larga sin cargar todos los elementos a la vez, solo los visibles.
    LazyColumn{

        // items() recorre la lista y dibuja un elemento por cada Pokémon.
        items(listaPokemon) { pokemon ->

            Row(modifier = Modifier.fillMaxWidth()
                .background(pokemon.tipo.color.copy(alpha = 0.4f))
                // Borde negro alrededor de cada elemento
                .border(BorderStroke(1.dp, Color.Black))
                .clickable {
                    onPokemonClick(
                        pokemon,
                        { pokemonSeleccionado = it },
                        { mostrarDialogo = it }
                    )
                }
            ){

                Image(painterResource( pokemon.imagen),
                    pokemon.nombre,
                    modifier = Modifier.size(60.dp))

                Column(){
                    Text(pokemon.nombre)
                    Text(pokemon.tipo.nombre)
                }

            }

            Spacer(Modifier.padding(4.dp))

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