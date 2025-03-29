package ir.iact.starwarsplanets.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListScreen
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListViewModel
import ir.iact.starwarsplanets.ui.theme.StarWarsPlanetsTheme
import kotlinx.serialization.Serializable

@Serializable
object PlanetList

@Serializable
data class PlanetDetail(val planetName: String)

@Composable
fun MainScreen() {

    StarWarsPlanetsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val navController = rememberNavController()

            NavHost(navController, startDestination = PlanetList) {
                composable<PlanetList> {
                    val viewModel = hiltViewModel<PlanetListViewModel>()
                    val state = viewModel.uiState.collectAsStateWithLifecycle().value

                    PlanetListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onUiInteraction = {}
                    )
                }

                composable<PlanetDetail> {
                    val args = it.toRoute<PlanetDetail>()
                    PlanetDetailScreen(
                        modifier = Modifier.padding(innerPadding),
                        planet = Planet(
                            name = args.planetName,
                            climate = "Tropical",
                            population = 7_000_000_000
                        )
                    )
                }
            }
        }
    }
}