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
import com.example.pokedexinteractiva.model.Rol

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    email: String,
    password: String,
    rolSeleccionado: Rol,
    error: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRolChange: (Rol) -> Unit,
    onRegisterClick: () -> Unit,
    onVolverLogin: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
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
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "Crear cuenta",
                        style = MaterialTheme.typography.headlineSmall,
                        color = rojoPokedex
                    )
                    Text(
                        text = "Regístrate para acceder a la Pokédex",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = { Text("Email") },
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    label = { Text("Contraseña") },
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    visualTransformation = if (mostrarPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { mostrarPassword = !mostrarPassword }) {
                            Icon(
                                imageVector = if (mostrarPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = if (mostrarPassword) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = rolSeleccionado.name,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Rol") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        Rol.values().forEach { rol ->
                            DropdownMenuItem(
                                text = { Text(rol.name) },
                                onClick = {
                                    onRolChange(rol)
                                    expanded = false
                                }
                            )
                        }
                    }
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

                Button(
                    onClick = onRegisterClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = rojoPokedex)
                ) {
                    Text("Registrar", color = Color.White)
                }

                TextButton(
                    onClick = onVolverLogin,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Volver al login", color = rojoPokedex)
                }
            }
        }
    }
}
