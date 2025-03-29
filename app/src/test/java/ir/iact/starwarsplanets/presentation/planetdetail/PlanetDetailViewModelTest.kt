package ir.iact.starwarsplanets.presentation.planetdetail

import CoroutineTestRule
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import ir.iact.starwarsplanets.presentation.PlanetDetailDestination
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import stub.FakePlanetUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class PlanetDetailViewModelTest {

    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    private val planetUseCase = FakePlanetUseCase()

    private val savedStateHandle: SavedStateHandle = mockk {
        mockkStatic("androidx.navigation.SavedStateHandleKt")
        every { toRoute<PlanetDetailDestination>() } returns PlanetDetailDestination("Earth")
    }

    private lateinit var viewModel: PlanetDetailViewModel

    @Test
    fun `GIVEN fetch data successfully WHEN view model is initialized THEN show data`() = runTest {
        viewModel = PlanetDetailViewModel(planetUseCase, savedStateHandle)

        viewModel.uiState.test {
            val initialState = awaitItem()
            assertEquals(PlanetDetailContract.UiState.Empty, initialState)
            assertEquals(
                PlanetDetailContract.UiState(
                    planet = planetUseCase.planetsMap.values.first()
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `GIVEN fetch data failed WHEN view model is initialized THEN send back event`() = runTest {
        planetUseCase.setShouldReturnError(true)
        viewModel = PlanetDetailViewModel(planetUseCase, savedStateHandle)

        viewModel.event.test {
            viewModel.uiState.test {
                awaitItem()
                advanceTimeBy(100)
                cancelAndIgnoreRemainingEvents()
            }

            assertEquals(
                PlanetDetailContract.Event.NavigateBack,
                awaitItem()
            )
        }
    }
}