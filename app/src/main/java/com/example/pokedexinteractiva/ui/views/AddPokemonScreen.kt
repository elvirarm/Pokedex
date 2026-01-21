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
import com.example.pokedexinteractiva.R
import com.example.pokedexinteractiva.model.Pokemon
import com.example.pokedexinteractiva.model.Tipo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPokemonScreen(
    onVolver: () -> Unit,
    onGuardar: (Pokemon) -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    val tipos = Tipo.values().toList()
    var tipoSeleccionado by remember { mutableStateOf(tipos.first()) }

    var expanded by remember { mutableStateOf(false) }
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
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Añadir Pokémon",
                        style = MaterialTheme.typography.headlineSmall,
                        color = rojoPokedex
                    )
                    Text(
                        text = "Pantalla de administrador",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                // Nombre
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it; error = null },
                    label = { Text("Nombre") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it; error = null },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = tipoSeleccionado.nombre,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Tipo") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        tipos.forEach { tipo ->
                            DropdownMenuItem(
                                text = { Text(tipo.nombre) },
                                onClick = {
                                    tipoSeleccionado = tipo
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Error bonito tipo chip
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

                Button(
                    onClick = {
                        val n = nombre.trim()
                        val d = descripcion.trim()

                        if (n.isBlank()) {
                            error = "El nombre no puede estar vacío"
                            return@Button
                        }
                        if (d.isBlank()) {
                            error = "La descripción no puede estar vacía"
                            return@Button
                        }

                        val nuevo = Pokemon(
                            nombre = n,
                            tipo = tipoSeleccionado,
                            imagen = R.drawable.pokeball, // placeholder
                            descripcion = d
                        )

                        onGuardar(nuevo)
                        onVolver()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = rojoPokedex)
                ) {
                    Text("Guardar", color = Color.White)
                }

                OutlinedButton(
                    onClick = onVolver,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Volver", color = rojoPokedex)
                }

                AssistChip(
                    onClick = { },
                    label = { Text("La imagen se asigna por defecto (pokeball).") }
                )
            }
        }
    }
}
