package ir.iact.starwarsplanets.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.iact.starwarsplanets.core.ui.theme.StarWarsPlanetsTheme
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailContract
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailScreen
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailViewModel
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListScreen
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListViewModel
import kotlinx.serialization.Serializable

@Serializable
object PlanetListDestination

@Serializable
data class PlanetDetailDestination(val planetName: String)

@Composable
fun MainScreen() {

    StarWarsPlanetsTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val navController = rememberNavController()

            NavHost(navController, startDestination = PlanetListDestination) {
                composable<PlanetListDestination> {
                    val viewModel = hiltViewModel<PlanetListViewModel>()
                    val state = viewModel.uiState.collectAsStateWithLifecycle().value

                    val lifecycleOwner = LocalLifecycleOwner.current
                    LaunchedEffect(lifecycleOwner.lifecycle) {
                        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            viewModel.event.collect {
                                when (it) {
                                    is PlanetListContract.Event.NavigateToPlanetDetail -> {
                                        navController.navigate(it.destination)
                                    }
                                }
                            }
                        }
                    }

                    PlanetListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onUiInteraction = viewModel::onUiInteraction
                    )
                }

                composable<PlanetDetailDestination> {
                    val viewModel = hiltViewModel<PlanetDetailViewModel>()
                    val state = viewModel.uiState.collectAsStateWithLifecycle().value

                    val lifecycleOwner = LocalLifecycleOwner.current
                    LaunchedEffect(lifecycleOwner.lifecycle) {
                        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                            viewModel.event.collect {
                                when (it) {
                                    PlanetDetailContract.Event.NavigateBack -> {
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                    }

                    PlanetDetailScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state
                    )
                }
            }
        }
    }
}