package com.example.pokedexinteractiva.ui.views

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexinteractiva.data.listaPokemon
import com.example.pokedexinteractiva.model.Pokemon
import com.example.pokedexinteractiva.navigation.Rutas
import com.example.pokedexinteractiva.viewmodel.AuthViewModel

@Composable
fun PokedexHomeScreen(
    authViewModel: AuthViewModel,
    navControllerPadre: NavController,
    lista: List<Pokemon>
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar {
                NavigationBar {
                    BotonesNavegador(navController, "pokemonList", "Lista")
                    BotonesNavegador(navController, "pokemonGrid", "Grid")
                    BotonesNavegador(navController, "pokemonCabecera", "Tipos")

                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            authViewModel.logout()
                            navControllerPadre.navigate(Rutas.LOGIN) {
                                popUpTo(0)
                            }
                        },
                        icon = { Text("Salir") }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "pokemonList",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("pokemonList") { PokemonList(lista) }
            composable("pokemonGrid") { PokemonGrid(lista) }
            composable("pokemonCabecera") { CabeceraPorTipos(lista) }
        }
    }
}

@Composable
fun RowScope.BotonesNavegador(navController: NavController, ruta: String, textoBoton: String) {
    NavigationBarItem(
        selected = false,
        onClick = { navController.navigate(ruta) },
        icon = { Text(text = textoBoton) }
    )
}
