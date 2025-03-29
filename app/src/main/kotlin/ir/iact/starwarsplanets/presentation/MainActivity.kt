package ir.iact.starwarsplanets.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import ir.iact.starwarsplanets.ui.theme.StarWarsPlanetsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<PlanetListViewModel>()
            val state = viewModel.uiState.collectAsStateWithLifecycle().value

            StarWarsPlanetsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PlanetListScreen(
                        modifier = Modifier.padding(innerPadding),
                        planets = state.planets

                    )
                }
            }
        }
    }
}