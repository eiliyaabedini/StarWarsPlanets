package ir.iact.starwarsplanets.presentation.planetlist

import ir.iact.starwarsplanets.domain.model.Planet

object PlanetListContract {

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

    sealed class UiInteraction {
        data class OnPlanetClicked(val planet: Planet) : UiInteraction()
        data object OnRetryClicked : UiInteraction()
    }
}