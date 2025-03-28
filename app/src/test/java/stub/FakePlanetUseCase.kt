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
            population = 7_000_000_000
        ),
        "Mars" to Planet(
            name = "Mars",
            climate = "Cold",
            population = 0
        ),
        "Jupiter" to Planet(
            name = "Jupiter",
            climate = "Hot",
            population = 1_000_000_000
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