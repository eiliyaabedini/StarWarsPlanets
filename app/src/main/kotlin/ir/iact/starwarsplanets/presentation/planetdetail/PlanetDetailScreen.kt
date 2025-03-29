package ir.iact.starwarsplanets.presentation.planetdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailContract.UiState

@Composable
fun PlanetDetailScreen(
    modifier: Modifier = Modifier,
    state: UiState,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val planet = state.planet
        Text(
            text = planet.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(label = "Climate", value = planet.climate)
        InfoRow(label = "Terrain", value = planet.terrain)
        InfoRow(
            label = "Population",
            value = planet.population?.toString()
                ?: stringResource(ir.iact.starwarsplanets.R.string.unknown)
        )
        InfoRow(label = "Diameter", value = planet.diameter.toString())
        InfoRow(label = "Gravity", value = planet.gravity)
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold
        )
        Text(text = value)
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetDetailScreenPreview() {
    val samplePlanet = Planet(
        name = "Tatooine",
        climate = "arid",
        terrain = "desert",
        population = 200000,
        diameter = 10465,
        gravity = "1 standard"
    )
    PlanetDetailScreen(
        state = UiState(
            planet = samplePlanet,
        )
    )
}


