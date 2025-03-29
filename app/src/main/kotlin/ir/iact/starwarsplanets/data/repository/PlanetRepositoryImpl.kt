package ir.iact.starwarsplanets.data.repository

import ir.iact.starwarsplanets.data.local.PlanetCache
import ir.iact.starwarsplanets.data.remote.PlanetApiService
import ir.iact.starwarsplanets.data.remote.model.PlanetDto
import ir.iact.starwarsplanets.domain.model.Planet
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val planetApiService: PlanetApiService,
    private val planetCache: PlanetCache
) : PlanetRepository {

    override suspend fun getPlanets(): List<Planet> {
        return planetApiService.getPlanets().results
            .map { it.toDomain() }.also {
                planetCache.setPlanets(it)
            }
    }

    override suspend fun getPlanet(name: String): Planet {
        return planetCache.getPlanet(name) ?: throw Exception("Planet not found")
    }
}

private fun PlanetDto.toDomain(): Planet = Planet(
    name = name,
    climate = climate,
    population = population.toLongOrNull(),
    diameter = diameter.toInt(),
    gravity = gravity,
    terrain = terrain
)





