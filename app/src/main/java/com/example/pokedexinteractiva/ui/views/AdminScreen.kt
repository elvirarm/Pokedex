package com.example.pokedexinteractiva.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AdminScreen(
    onLogout: () -> Unit
) {
    val rojoPokedex = Color(0xFFE53935)
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Panel de Administrador",
                        style = MaterialTheme.typography.headlineSmall,
                        color = rojoPokedex
                    )
                    Text(
                        text = "Acceso exclusivo",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                HorizontalDivider()

                Text(
                    text = "Desde aquí puedes gestionar funcionalidades avanzadas de la aplicación:",
                    style = MaterialTheme.typography.bodyMedium
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("• Añadir y editar Pokémon")
                    Text("• Gestionar datos y estadísticas")
                    Text("• Acceso a herramientas de administración")
                }

                Spacer(modifier = Modifier.height(8.dp))

                AssistChip(
                    onClick = { },
                    label = { Text("Rol: Administrador") },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = rojoPokedex,
                        labelColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onLogout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = rojoPokedex)
                ) {
                    Text("Cerrar sesión", color = Color.White)
                }
            }
        }
    }
}
