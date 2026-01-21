package com.example.pokedexinteractiva.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pokedexinteractiva.viewmodel.LoginUiState

@Composable
fun LoginScreen(
    ui: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var mostrarPassword by remember { mutableStateOf(false) }

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
                Column {
                    Text(
                        text = "Pokédex",
                        style = MaterialTheme.typography.headlineMedium,
                        color = rojoPokedex
                    )
                    Text(
                        text = "Inicia sesión para continuar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                OutlinedTextField(
                    value = ui.email,
                    onValueChange = onEmailChange,
                    label = { Text("Email") },
                    leadingIcon = {
                        Icon(Icons.Default.Email, contentDescription = null)
                    },
                    singleLine = true,
                    isError = ui.errorEmail != null,
                    supportingText = {
                        ui.errorEmail?.let { Text(it) }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = ui.password,
                    onValueChange = onPasswordChange,
                    label = { Text("Contraseña") },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null)
                    },
                    visualTransformation = if (mostrarPassword)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                            Icon(
                                imageVector = if (mostrarPassword)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility,
                                contentDescription = null
                            )
                        }
                    },
                    singleLine = true,
                    isError = ui.errorPassword != null,
                    supportingText = {
                        ui.errorPassword?.let { Text(it) }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                ui.errorGeneral?.let {
                    AssistChip(
                        onClick = {},
                        label = { Text(it) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = rojoClaro,
                            labelColor = Color.Black
                        )
                    )
                }

                Button(
                    onClick = onLoginClick,
                    enabled = !ui.cargando,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = rojoPokedex
                    )
                ) {
                    if (ui.cargando) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Accediendo…", color = Color.White)
                    } else {
                        Text("Acceder", color = Color.White)
                    }
                }

                TextButton(
                    onClick = onRegisterClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Crear cuenta", color = rojoPokedex)
                }

                AssistChip(
                    onClick = {},
                    label = {
                        Text("Demo: user@demo.com / 123456  |  admin@demo.com / admin123")
                    }
                )
            }
        }
    }
}
