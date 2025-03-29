package ir.iact.starwarsplanets.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.UiState
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListScreen
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PlanetListScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showPlanetListWhenDataAvailable() {
        val planet = Planet(
            name = "Earth",
            climate = "Tropical",
            population = 7_000_000_000
        )
        val planet2 = Planet(
            name = "Mars",
            climate = "Cold",
            population = 0
        )

        composeTestRule.setContent {
            PlanetListScreen(
                state = UiState(
                    planets = listOf(planet, planet2),
                    isLoading = false
                ),
                onUiInteraction = {}
            )
        }

        composeTestRule.onNodeWithText("Earth").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mars").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tropical").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cold").assertIsDisplayed()
        composeTestRule.onNodeWithText("Population: 7000000000").assertIsDisplayed()
        composeTestRule.onNodeWithText("Population: 0").assertIsDisplayed()
    }

    @Test
    fun showLoadingWhenDataIsLoading() {
        val planet = Planet(
            name = "Earth",
            climate = "Tropical",
            population = 7_000_000_000
        )
        composeTestRule.setContent {
            PlanetListScreen(
                state = UiState(
                    planets = listOf(planet),
                    isLoading = true
                ),
                onUiInteraction = {}
            )
        }

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
        composeTestRule.onNodeWithText("Earth").assertIsNotDisplayed()
    }

    @Test
    fun showErrorWithRetryButtonWhenDataHasError() {
        val planet = Planet(
            name = "Earth",
            climate = "Tropical",
            population = 7_000_000_000
        )
        composeTestRule.setContent {
            PlanetListScreen(
                state = UiState(
                    planets = listOf(planet),
                    isLoading = false,
                    hasError = "Error happened while fetching data"
                ),
                onUiInteraction = {}
            )
        }

        composeTestRule.onNodeWithText("Error happened while fetching data").assertIsDisplayed()
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
        composeTestRule.onNodeWithTag("loading").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("Earth").assertIsNotDisplayed()
    }

    @Test
    fun onRetryClickedShouldTriggerUiInteractionForRetry() {
        val planet = Planet(
            name = "Earth",
            climate = "Tropical",
            population = 7_000_000_000
        )
        var retryButtonClicked: Boolean = false
        composeTestRule.setContent {
            PlanetListScreen(
                state = UiState(
                    planets = listOf(planet),
                    isLoading = false,
                    hasError = "Error happened while fetching data"
                ),
                onUiInteraction = {
                    if (it is PlanetListContract.UiInteraction.OnRetryClicked) {
                        retryButtonClicked = true
                    }
                }
            )
        }

        composeTestRule.onNodeWithText("Retry").performClick()
        assertEquals(true, retryButtonClicked)
    }

    @Test
    fun onItemClickedTriggerUiInteractionForPlanetSelected() {
        val planet = Planet(
            name = "Earth",
            climate = "Tropical",
            population = 7_000_000_000
        )
        var selectedPlanet: Planet? = null
        composeTestRule.setContent {
            PlanetListScreen(
                state = UiState(
                    planets = listOf(planet),
                    isLoading = false,
                    hasError = "Error happened while fetching data"
                ),
                onUiInteraction = {
                    if (it is PlanetListContract.UiInteraction.OnPlanetClicked) {
                        selectedPlanet = it.planet
                    }
                }
            )
        }

        composeTestRule.onNodeWithText("Earth").onParent().performClick()
        assertEquals(planet, selectedPlanet)
    }


}