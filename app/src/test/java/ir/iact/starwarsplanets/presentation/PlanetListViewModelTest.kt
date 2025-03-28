package ir.iact.starwarsplanets.presentation

import CoroutineTestRule
import app.cash.turbine.test
import ir.iact.starwarsplanets.presentation.model.PlanetListUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import stub.FakePlanetUseCase

class PlanetListViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val planetUseCase = FakePlanetUseCase()

    private var viewModel = PlanetListViewModel(
        planetUseCase = planetUseCase
    )

    @Test
    fun `GIVEN data is loading WHEN view model is initialized THEN show loading`() = runTest {
        viewModel = PlanetListViewModel(planetUseCase)

        viewModel.uiState.test {
            // First emission should be initial Empty state
            assertEquals(PlanetListUiState.Empty, awaitItem())

            // Then we expect the loading state emitted from initiateData()
            assertEquals(
                PlanetListUiState(isLoading = true, planets = emptyList()),
                awaitItem()
            )
        }
    }
}