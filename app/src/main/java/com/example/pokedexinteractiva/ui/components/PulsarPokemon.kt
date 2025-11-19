package com.example.pokedexinteractiva.ui.components

import com.example.pokedexinteractiva.model.Pokemon

// Función auxiliar que centraliza la lógica al pulsar sobre un Pokémon.
// Se usa en varias vistas (lista, grid, cabecera por tipos) para no repetir código.
fun onPokemonClick(
    pokemon: Pokemon,                          // Pokémon que se ha pulsado
    setPokemonSeleccionado: (Pokemon) -> Unit, // Función que actualiza el Pokémon seleccionado
    setMostrarDialogo: (Boolean) -> Unit       // Función que controla si el diálogo se muestra o no
) {
    // Guardamos el Pokémon seleccionado
    setPokemonSeleccionado(pokemon)

    // Activamos el diálogo para mostrar sus detalles
    setMostrarDialogo(true)
}
