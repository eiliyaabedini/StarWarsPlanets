package ir.iact.starwarsplanets.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailContract.UiState
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailScreen
import org.junit.Rule
import org.junit.Test

class PlanetDetailScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showPlanetDetailItems() {
        composeTestRule.setContent {
            PlanetDetailScreen(
                state = UiState(
                    planet = PlanetData.planet3,
                )
            )
        }

        composeTestRule.onNodeWithText("Tatooine").assertIsDisplayed()
        composeTestRule.onNodeWithText("arid").assertIsDisplayed()
        composeTestRule.onNodeWithText("Population: 200000").assertIsDisplayed()
        composeTestRule.onNodeWithText("Diameter: 10465").assertIsDisplayed()
        composeTestRule.onNodeWithText("Gravity: 1 standard").assertIsDisplayed()
        composeTestRule.onNodeWithText("Terrain: desert").assertIsDisplayed()
    }
}