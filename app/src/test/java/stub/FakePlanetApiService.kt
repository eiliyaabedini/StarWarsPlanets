package stub

import ir.iact.starwarsplanets.data.remote.PlanetApiService
import ir.iact.starwarsplanets.data.remote.model.PlanetDto
import ir.iact.starwarsplanets.data.remote.model.PlanetResponseDto

class FakePlanetApiService : PlanetApiService {

    private var throwError = false
    private var planets: List<PlanetDto> = listOf()

    override suspend fun getPlanets(page: Int): PlanetResponseDto {
        if (throwError)
            throw Exception("Error happened in api service")

        return PlanetResponseDto(
            count = planets.size,
            next = null,
            previous = null,
            results = planets
        )
    }

    fun setPlanets(newPlanets: List<PlanetDto>) {
        planets = newPlanets
    }

    fun setThrowError(value: Boolean) {
        throwError = value
    }
}