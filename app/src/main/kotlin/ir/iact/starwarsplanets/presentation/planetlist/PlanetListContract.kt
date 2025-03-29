package ir.iact.starwarsplanets.presentation.planetlist

import androidx.compose.runtime.Stable
import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.presentation.PlanetDetailDestination

object PlanetListContract {

    @Stable
    data class UiState(
        val isLoading: Boolean,
        val planets: List<Planet>,
        val hasError: String? = null
    ) {
        companion object {
            val Empty = UiState(
                isLoading = false,
                planets = emptyList()
            )
        }
    }

    sealed class Event {
        data class NavigateToPlanetDetail(val destination: PlanetDetailDestination) : Event()
    }

    sealed class UiInteraction {
        data class OnPlanetClicked(val planet: Planet) : UiInteraction()
        data object OnRetryClicked : UiInteraction()
    }
}