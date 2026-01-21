package com.example.pokedexinteractiva.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexinteractiva.data.PokemonRepository
import com.example.pokedexinteractiva.model.Rol
import com.example.pokedexinteractiva.ui.views.AddPokemonScreen
import com.example.pokedexinteractiva.ui.views.LoginScreen
import com.example.pokedexinteractiva.ui.views.MenuScreen
import com.example.pokedexinteractiva.ui.views.PokedexHomeScreen
import com.example.pokedexinteractiva.ui.views.RegisterScreen
import com.example.pokedexinteractiva.viewmodel.AuthViewModel

@Composable
fun NavGraph(authViewModel: AuthViewModel) {

    val navController = rememberNavController()
    val ui by authViewModel.ui.collectAsState()
    val pokemonRepo = remember { PokemonRepository() }

    val startDestination = if (ui.sesion.logueado) Rutas.MENU else Rutas.LOGIN

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Rutas.REGISTER) {
            RegisterScreen(
                email = ui.email,
                password = ui.password,
                rolSeleccionado = ui.rolSeleccionado ?: Rol.USUARIO,
                error = ui.errorGeneral,
                onEmailChange = authViewModel::onEmailChange,
                onPasswordChange = authViewModel::onPasswordChange,
                onRolChange = authViewModel::onRolChange,
                onRegisterClick = {
                    authViewModel.registrar {
                        navController.popBackStack()
                    }
                },
                onVolverLogin = { navController.popBackStack() }
            )
        }

        composable(Rutas.LOGIN) {
            LoginScreen(
                ui = ui,
                onEmailChange = authViewModel::onEmailChange,
                onPasswordChange = authViewModel::onPasswordChange,
                onLoginClick = { authViewModel.login() },
                onRegisterClick = { navController.navigate(Rutas.REGISTER) }
            )

            LaunchedEffect(ui.sesion) {
                if (ui.sesion.logueado) {
                    navController.navigate(Rutas.MENU) {
                        popUpTo(Rutas.LOGIN) { inclusive = true }
                    }
                }
            }
        }

        composable(Rutas.MENU) {
            MenuScreen(
                navController = navController,
                rol = ui.sesion.rol
            )
        }

        composable(Rutas.POKEDEX) {
            PokedexHomeScreen(
                authViewModel = authViewModel,
                navControllerPadre = navController,
                lista = pokemonRepo.pokemons
            )
        }

        composable(Rutas.ADD_POKEMON) {
            if (ui.sesion.rol == Rol.ADMINISTRADOR) {
                AddPokemonScreen(
                    onVolver = { navController.popBackStack() },
                    onGuardar = { pokemonRepo.addPokemon(it) }
                )
            }
        }

    }
}
