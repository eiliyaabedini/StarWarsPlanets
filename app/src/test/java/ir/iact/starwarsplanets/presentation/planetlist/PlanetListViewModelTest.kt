package ir.iact.starwarsplanets.presentation.planetlist

import CoroutineTestRule
import app.cash.turbine.test
import ir.iact.starwarsplanets.presentation.PlanetDetailDestination
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.Event
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.UiInteraction
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.UiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import stub.FakePlanetUseCase
import stub.PlanetData

@OptIn(ExperimentalCoroutinesApi::class)
class PlanetListViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val planetUseCase = FakePlanetUseCase()

    private lateinit var viewModel: PlanetListViewModel

    @Test
    fun `GIVEN data is loading WHEN view model is initialized THEN show loading`() = runTest {
        viewModel = PlanetListViewModel(planetUseCase)

        viewModel.uiState.test {
            // First emission should be initial Empty state
            assertEquals(UiState.Empty, awaitItem())

            // Then we expect the loading state emitted from initiateData()
            assertEquals(
                UiState.Empty.copy(isLoading = true),
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
            assertEquals(UiState.Empty, initialState)

            // Then we expect the loading state emitted from initiateData()
            assertEquals(
                UiState.Empty.copy(isLoading = true),
                awaitItem()
            )

            advanceTimeBy(1000)

            // Then we expect the success state to emit list of planets from initiateData()
            assertEquals(
                UiState(
                    isLoading = false,
                    planets = PlanetData.planetsMap.values.toList()
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
            assertEquals(UiState.Empty, initialState)

            // Then we expect the loading state emitted from initiateData()
            assertEquals(
                UiState.Empty.copy(isLoading = true),
                awaitItem()
            )

            advanceTimeBy(1000)

            // Then we expect the success state to emit list of planets from initiateData()
            assertEquals(
                UiState(
                    isLoading = false,
                    hasError = "Error",
                    planets = emptyList()
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `GIVEN OnRetryClicked WHEN onUiInteraction THEN retry fetching data`() = runTest {
        planetUseCase.setShouldReturnError(true)
        viewModel = PlanetListViewModel(planetUseCase)

        viewModel.uiState.test {
            assertEquals(UiState.Empty, awaitItem())
            assertEquals(
                UiState.Empty.copy(isLoading = true),
                awaitItem()
            )
            advanceTimeBy(1000)
            assertEquals(
                UiState(
                    isLoading = false,
                    hasError = "Error",
                    planets = emptyList()
                ),
                awaitItem()
            )

            planetUseCase.setShouldReturnError(false)
            viewModel.onUiInteraction(UiInteraction.OnRetryClicked)
            assertEquals(
                UiState.Empty.copy(isLoading = true),
                awaitItem()
            )
            advanceTimeBy(1000)
            assertEquals(
                UiState(
                    isLoading = false,
                    planets = PlanetData.planetsMap.values.toList()
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `GIVEN OnPlanetClicked WHEN onUiInteraction THEN send NavigateToPlanetDetail event`() =
        runTest {
            viewModel = PlanetListViewModel(planetUseCase)
            val selectedPlanet = PlanetData.planetEarth

            viewModel.event.test {
                viewModel.onUiInteraction(UiInteraction.OnPlanetClicked(selectedPlanet))

                assertEquals(
                    Event.NavigateToPlanetDetail(PlanetDetailDestination(selectedPlanet.name)),
                    awaitItem()
                )
            }
        }
}