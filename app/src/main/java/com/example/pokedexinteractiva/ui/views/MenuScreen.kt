package com.example.pokedexinteractiva.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokedexinteractiva.model.Rol
import com.example.pokedexinteractiva.navigation.Rutas

@Composable
fun MenuScreen(
    navController: NavController,
    rol: Rol?
) {
    var error by remember { mutableStateOf<String?>(null) }

    val rojoPokedex = Color(0xFFE53935)
    val rojoClaro = Color(0xFFFFCDD2)
    val fondoGradiente = Brush.verticalGradient(
        listOf(
            Color(0xFFFFEBEE),
            Color(0xFFFFFFFF)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoGradiente)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Header
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Menú",
                        style = MaterialTheme.typography.headlineSmall,
                        color = rojoPokedex
                    )
                    Text(
                        text = if (rol == Rol.ADMINISTRADOR) "Modo administrador" else "Modo usuario",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                HorizontalDivider()

                Button(
                    onClick = {
                        error = null
                        navController.navigate(Rutas.POKEDEX)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = rojoPokedex)
                ) {
                    Text("Ver Pokémon", color = Color.White)
                }

                OutlinedButton(
                    onClick = {
                        if (rol == Rol.ADMINISTRADOR) {
                            error = null
                            navController.navigate(Rutas.ADD_POKEMON)
                        } else {
                            error = "No tienes permisos para añadir Pokémon."
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Añadir Pokémon", color = rojoPokedex)
                }

                error?.let {
                    AssistChip(
                        onClick = { },
                        label = { Text(it) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = rojoClaro,
                            labelColor = Color.Black
                        )
                    )
                }

                AssistChip(
                    onClick = { },
                    label = {
                        Text(
                            text = "Rol: ${rol?.name ?: "DESCONOCIDO"}"
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color(0xFFF5F5F5),
                        labelColor = Color.DarkGray
                    )
                )
            }
        }
    }
}
