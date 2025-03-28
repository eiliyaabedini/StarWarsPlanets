package ir.iact.starwarsplanets.presentation

import junit.framework.TestCase.assertEquals
import org.junit.Test
import stub.FakePlanetUseCase

class PlanetListViewModelTest {

    private val planetUseCase = FakePlanetUseCase()

    private val viewModel = PlanetListViewModel(
        planetUseCase = planetUseCase
    )

    @Test
    fun `when view model is initialized, planets are loaded`() {
        val planets = viewModel.uiState.value.planets
        assertEquals(3, planets.size)
        assertEquals("Earth", planets[0].name)
        assertEquals("Mars", planets[1].name)
        assertEquals("Jupiter", planets[2].name)
    }
}