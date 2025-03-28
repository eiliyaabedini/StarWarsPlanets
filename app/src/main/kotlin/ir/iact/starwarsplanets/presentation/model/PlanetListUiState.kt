package ir.iact.starwarsplanets.presentation.model

import ir.iact.starwarsplanets.domain.model.Planet

data class PlanetListUiState(
    val isLoading: Boolean,
    val planets: List<Planet>,
    val hasError: String? = null
) {
    companion object {
        val Empty = PlanetListUiState(
            isLoading = false,
            planets = emptyList()
        )
    }
}