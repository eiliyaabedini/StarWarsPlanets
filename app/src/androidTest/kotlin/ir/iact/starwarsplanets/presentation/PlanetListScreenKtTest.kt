package ir.iact.starwarsplanets.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import ir.iact.starwarsplanets.domain.model.Planet
import org.junit.Rule
import org.junit.Test

class PlanetListScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPlanetItem() {
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
                planets = listOf(planet, planet2),
            )
        }

        composeTestRule.onNodeWithText("Earth").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mars").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tropical").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cold").assertIsDisplayed()
        composeTestRule.onNodeWithText("Population: 7000000000").assertIsDisplayed()
        composeTestRule.onNodeWithText("Population: 0").assertIsDisplayed()
    }

}