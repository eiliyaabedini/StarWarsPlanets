package ir.iact.starwarsplanets.data.repository

import ir.iact.starwarsplanets.data.remote.PlanetApiService
import ir.iact.starwarsplanets.data.remote.model.PlanetDto
import ir.iact.starwarsplanets.domain.model.Planet
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val planetApiService: PlanetApiService
) : PlanetRepository {

    override suspend fun getPlanets(): List<Planet> {
        return planetApiService.getPlanets().results.map { it.toDomain() }
    }
}

private fun PlanetDto.toDomain(): Planet = Planet(
    name = name,
    climate = climate,
    population = population.toLongOrNull()
)





