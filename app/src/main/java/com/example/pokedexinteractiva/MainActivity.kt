package com.example.pokedexinteractiva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.pokedexinteractiva.ui.theme.PokedexInteractivaTheme
import com.example.pokedexinteractiva.ui.views.CabeceraPorTipos
import com.example.pokedexinteractiva.ui.views.PokemonGrid
import com.example.pokedexinteractiva.ui.views.PokemonList

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Creamos el navController, que controla a qué pantalla navegamos
            val navController = rememberNavController()

            PokedexInteractivaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar{

                            // NavigationBar contiene los botones de navegación
                            NavigationBar {

                                // Cada botón llama a una ruta distinta
                                BotonesNavegador(navController, "pokemonList", "Lista")
                                BotonesNavegador(navController, "pokemonGrid", "Grid")
                                BotonesNavegador(navController, "pokemonCabecera", "Tipos")

                            }

                        }
                    }
                    ) { innerPadding ->

                    // NavHost contiene todas las pantallas y gestiona la navegación entre ellas.
                    // startDestination indica en qué pantalla empieza la app.
                        NavHost(navController, startDestination = "pokemonList", modifier = Modifier.padding(innerPadding)){

                            composable("pokemonList") { PokemonList(listaPokemon) }
                            composable("pokemonGrid") { PokemonGrid(listaPokemon)}
                            composable("pokemonCabecera") { CabeceraPorTipos(listaPokemon)}

                        }

                }
            }
        }
    }
}

// Función que crea un botón de navegación en la bottom bar.
@Composable
fun RowScope.BotonesNavegador(navController: NavController, ruta: String, textoBoton: String){
    NavigationBarItem(
        selected = false,
        onClick = {
            // Navega a la ruta que corresponde con el botón pulsado.
            navController.navigate(ruta)
        },
        icon = {Text(textoBoton)}
    )
}