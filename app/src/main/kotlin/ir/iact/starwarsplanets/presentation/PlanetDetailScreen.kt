package ir.iact.starwarsplanets.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.presentation.planetlist.PlanetItem

@Composable
fun PlanetDetailScreen(
    modifier: Modifier = Modifier,
    planet: Planet,
) {
    PlanetItem(
        planet = planet,
    )
}