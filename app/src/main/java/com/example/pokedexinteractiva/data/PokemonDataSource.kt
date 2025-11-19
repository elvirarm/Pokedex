package com.example.pokedexinteractiva.data

import com.example.pokedexinteractiva.R
import com.example.pokedexinteractiva.model.Pokemon
import com.example.pokedexinteractiva.model.Tipo

// Lista de Pokémons que usaremos en las vistas.
// Cada Pokémon tiene nombre, tipo, imagen y una descripción.
val listaPokemon = listOf(
    Pokemon("Ampharos", Tipo.ELECTRICO, R.drawable.ampharos, "Su luz puede verse a kilómetros de distancia."),
    Pokemon("Charmander", Tipo.FUEGO, R.drawable.charmander, "La llama de su cola refleja su estado de salud."),
    Pokemon("Cubone", Tipo.TIERRA, R.drawable.cubone, "Lleva el cráneo de su madre como casco."),
    Pokemon("Diglett", Tipo.TIERRA, R.drawable.diglett, "Vive bajo tierra y asoma solo la cabeza."),
    Pokemon("Ditto", Tipo.NORMAL, R.drawable.ditto, "Capaz de transformarse en cualquier Pokémon."),
    Pokemon("Entei", Tipo.FUEGO, R.drawable.entei, "Corre tan rápido como un volcán en erupción."),
    Pokemon("Garchomp", Tipo.TIERRA, R.drawable.garchomp, "Se mueve a gran velocidad, como un jet."),
    Pokemon("Lapras", Tipo.AGUA, R.drawable.lapras, "Pokémon amable que transporta personas por el agua."),
    Pokemon("Moltres", Tipo.FUEGO, R.drawable.moltres, "Ave legendaria envuelta en llamas."),
    Pokemon("Noibat", Tipo.VOLADOR, R.drawable.noibat, "Usa ultrasonidos para orientarse en la oscuridad."),
    Pokemon("Pidgeot", Tipo.VOLADOR, R.drawable.pidgeot, "Vuela a velocidades increíbles para cazar."),
    Pokemon("Pikachu", Tipo.ELECTRICO, R.drawable.pikachu, "Acumula electricidad en sus mejillas."),
    Pokemon("Porygon", Tipo.NORMAL, R.drawable.porygon, "Pokémon sintético creado por programación."),
    Pokemon("Psyduck", Tipo.AGUA, R.drawable.psyduck, "Sufre dolores de cabeza que liberan poderes psíquicos."),
    Pokemon("Ratatta", Tipo.NORMAL, R.drawable.ratatta, "Un Pokémon muy rápido y territorial."),
    Pokemon("Smoliv", Tipo.PLANTA, R.drawable.smoliv, "Produce aceite amargo que usa para defenderse."),
    Pokemon("Sprigatito", Tipo.PLANTA, R.drawable.sprigatito, "Emite un dulce aroma al frotarse la cara."),
    Pokemon("Totodile", Tipo.AGUA, R.drawable.totodile, "Muerde todo lo que se mueve por curiosidad."),
    Pokemon("Venusaur", Tipo.PLANTA, R.drawable.venusaur, "El sol potencia la flor de su espalda."),
    Pokemon("Voltorb", Tipo.ELECTRICO, R.drawable.voltorb, "Explota si se siente amenazado."),
    Pokemon("Yveltal", Tipo.VOLADOR, R.drawable.yveltal, "Pokémon legendario que absorbe energía vital.")
)