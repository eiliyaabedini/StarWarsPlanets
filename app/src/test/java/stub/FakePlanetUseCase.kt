package stub

import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.domain.usecase.PlanetUseCase
import kotlinx.coroutines.delay

class FakePlanetUseCase : PlanetUseCase {

    private var shouldReturnError = false

    val planetsMap = mutableMapOf(
        "Earth" to Planet(
            name = "Earth",
            climate = "Tropical",
            population = 7_000_000_000,
            diameter = 12_756,
            gravity = "1 standard",
            terrain = "grasslands, mountains"
        ),
        "Mars" to Planet(
            name = "Mars",
            climate = "Cold",
            population = 0,
            diameter = 6_792,
            gravity = "0.3 standard",
            terrain = "deserts"
        ),
        "Jupiter" to Planet(
            name = "Jupiter",
            climate = "Hot",
            population = 1_000_000_000,
            diameter = 142_984,
            gravity = "2.5 standard",
            terrain = "gas giant"
        )

    )

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }


    override suspend fun getPlanets(): List<Planet> {
        delay(1000)
        if (shouldReturnError) {
            throw Exception("Error")
        }
        return planetsMap.values.toList()
    }

    override suspend fun getPlanet(name: String): Planet {
        delay(1000)
        if (shouldReturnError) {
            throw Exception("Error")
        }
        return planetsMap[name] ?: throw IllegalArgumentException("Planet not found")
    }
}