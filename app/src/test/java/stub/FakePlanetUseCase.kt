package stub

import ir.iact.starwarsplanets.domain.model.Planet
import ir.iact.starwarsplanets.domain.usecase.PlanetUseCase
import kotlinx.coroutines.delay

class FakePlanetUseCase : PlanetUseCase {

    private var shouldReturnError = false

    fun setShouldReturnError(value: Boolean) {
        shouldReturnError = value
    }


    override suspend fun getPlanets(): List<Planet> {
        delay(1000)
        if (shouldReturnError) {
            throw Exception("Error")
        }
        return PlanetData.planetsMap.values.toList()
    }

    override suspend fun getPlanet(name: String): Planet {
        delay(1000)
        if (shouldReturnError) {
            throw Exception("Error")
        }
        return PlanetData.planetsMap[name] ?: throw IllegalArgumentException("Planet not found")
    }
}