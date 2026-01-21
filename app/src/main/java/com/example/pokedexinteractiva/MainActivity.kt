package com.example.pokedexinteractiva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pokedexinteractiva.data.RepoAuth
import com.example.pokedexinteractiva.data.SesionStore
import com.example.pokedexinteractiva.data.db.AppDatabase
import com.example.pokedexinteractiva.navigation.NavGraph
import com.example.pokedexinteractiva.ui.theme.PokedexInteractivaTheme
import com.example.pokedexinteractiva.viewmodel.AuthViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getInstance(this)
        val dao = db.usuarioDao()

        val repo = RepoAuth(dao)
        lifecycleScope.launch {
            repo.asegurarUsuariosDemo()
        }

        val store = SesionStore(this)
        val authViewModel = AuthViewModel(repo, store)

        setContent {
            PokedexInteractivaTheme {
                NavGraph(authViewModel)
            }
        }
    }

}
