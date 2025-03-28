package ir.iact.starwarsplanets.presentation

import CoroutineTestRule
import app.cash.turbine.test
import ir.iact.starwarsplanets.presentation.model.PlanetListUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import stub.FakePlanetUseCase

@OptIn(ExperimentalCoroutinesApi::class)
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

    @Test
    fun `GIVEN fetch data successfully WHEN view model is initialized THEN show data`() = runTest {
        viewModel = PlanetListViewModel(planetUseCase)

        viewModel.uiState.test {
            // First emission should be initial Empty state
            val initialState = awaitItem()
            assertEquals(PlanetListUiState.Empty, initialState)

            // Then we expect the loading state emitted from initiateData()
            assertEquals(
                PlanetListUiState(isLoading = true, planets = emptyList()),
                awaitItem()
            )

            advanceTimeBy(1000)

            // Then we expect the success state to emit list of planets from initiateData()
            assertEquals(
                PlanetListUiState(
                    isLoading = false,
                    planets = planetUseCase.planetsMap.values.toList()
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `GIVEN fetch data failed WHEN view model is initialized THEN show error`() = runTest {
        planetUseCase.setShouldReturnError(true)
        viewModel = PlanetListViewModel(planetUseCase)

        viewModel.uiState.test {
            // First emission should be initial Empty state
            val initialState = awaitItem()
            assertEquals(PlanetListUiState.Empty, initialState)

            // Then we expect the loading state emitted from initiateData()
            assertEquals(
                PlanetListUiState(isLoading = true, planets = emptyList()),
                awaitItem()
            )

            advanceTimeBy(1000)

            // Then we expect the success state to emit list of planets from initiateData()
            assertEquals(
                PlanetListUiState(
                    isLoading = false,
                    hasError = "Error",
                    planets = planetUseCase.planetsMap.values.toList()
                ),
                awaitItem()
            )
        }
    }
}