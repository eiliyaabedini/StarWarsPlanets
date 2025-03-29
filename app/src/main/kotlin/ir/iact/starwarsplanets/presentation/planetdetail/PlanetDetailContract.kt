package ir.iact.starwarsplanets.presentation.planetdetail

import androidx.compose.runtime.Stable
import ir.iact.starwarsplanets.domain.model.Planet

object PlanetDetailContract {

    @Stable
    data class UiState(
        val planet: Planet,
    ) {
        companion object {
            val Empty = UiState(
                planet = Planet(
                    name = "",
                    climate = "",
                    terrain = "",
                    population = 0,
                    diameter = 0,
                    gravity = ""
                )
            )
        }
    }

    sealed class Event {
        data object NavigateBack : Event()
    }
}