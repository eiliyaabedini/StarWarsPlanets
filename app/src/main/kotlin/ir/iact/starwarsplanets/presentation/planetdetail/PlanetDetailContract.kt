package ir.iact.starwarsplanets.presentation.planetdetail

import androidx.compose.runtime.Stable
import ir.iact.starwarsplanets.domain.model.Planet

object PlanetDetailContract {

    @Stable
    data class UiState(
        val planet: Planet,
    )
}