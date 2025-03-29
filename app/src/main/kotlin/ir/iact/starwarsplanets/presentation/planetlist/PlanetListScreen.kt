package ir.iact.starwarsplanets.presentation.planetlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.iact.starwarsplanets.R
import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.UiInteraction
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.UiState

// Planet Compose Screen that shows list of planets using lazy column
@Composable
fun PlanetListScreen(
    modifier: Modifier = Modifier,
    state: UiState,
    onUiInteraction: (UiInteraction) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(state.planets.size) { index ->
            val planet = state.planets[index]
            PlanetItem(
                planet = planet,
                modifier = Modifier.clickable {
                    onUiInteraction(UiInteraction.OnPlanetClicked(planet))
                }
            )
        }
    }
}

@Composable
fun PlanetItem(
    planet: Planet,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = planet.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.climate),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = planet.climate, style = MaterialTheme.typography.bodyLarge)
            }
            Text(
                text = stringResource(
                    R.string.population,
                    planet.population ?: stringResource(R.string.unknown)
                ),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlanetItemPreview() {
    val planet = Planet(
        name = "Earth",
        climate = "Tropical",
        population = 7_000_000_000
    )
    val planet2 = Planet(
        name = "Mars",
        climate = "Cold",
        population = 0
    )
    PlanetListScreen(
        state = UiState(planets = listOf(planet, planet2), isLoading = false),
        modifier = Modifier.padding(16.dp),
        onUiInteraction = {}
    )
}