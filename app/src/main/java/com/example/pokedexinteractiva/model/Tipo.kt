package com.example.pokedexinteractiva.model

import androidx.compose.ui.graphics.Color


// Enum que contiene todos los tipos de Pokémon disponibles.
// Cada tipo tiene asociado un color (para usarlo en la interfaz)
// y un nombre en texto que podemos mostrar en pantalla.
enum class Tipo(val color: Color, val nombre: String) {
    FUEGO(Color(0xFFE39542), "Fuego"),
    AGUA(Color(0xFF6395E6), "Agua"),
    ELECTRICO(Color(0xFFE5D018), "Eléctrico"),
    NORMAL(Color(0xFFE6D8B3), "Normal"),
    VOLADOR (Color(0xFFB2B8E5), "Volador"),
    TIERRA(Color(0xFF987F56), "Tierra"),
    PLANTA(Color(0xFF6BE647), "Planta")

}