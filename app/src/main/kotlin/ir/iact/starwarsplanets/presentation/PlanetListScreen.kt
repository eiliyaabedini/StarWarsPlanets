package ir.iact.starwarsplanets.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.iact.starwarsplanets.domain.model.Planet

// Planet Compose Screen that shows list of planets using lazy column
@Composable
fun PlanetListScreen(
    modifier: Modifier = Modifier,
    planets: List<Planet>
) {

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
        planets = listOf(planet, planet2),
        modifier = Modifier.padding(16.dp)
    )
}