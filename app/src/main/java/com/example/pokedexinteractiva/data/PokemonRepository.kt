package com.example.pokedexinteractiva.data

import androidx.compose.runtime.mutableStateListOf
import com.example.pokedexinteractiva.model.Pokemon

class PokemonRepository {

    private val _pokemons = mutableStateListOf<Pokemon>().apply {
        addAll(listaPokemon)
    }

    val pokemons: List<Pokemon> get() = _pokemons

    fun addPokemon(pokemon: Pokemon) {
        _pokemons.add(pokemon)
    }
}
