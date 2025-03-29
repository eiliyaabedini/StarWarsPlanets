package ir.iact.starwarsplanets.presentation.planetdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailContract.UiState
import ir.iact.starwarsplanets.presentation.planetlist.PlanetItem

@Composable
fun PlanetDetailScreen(
    modifier: Modifier = Modifier,
    state: UiState,
) {
    PlanetItem(
        planet = state.planet,
    )
}