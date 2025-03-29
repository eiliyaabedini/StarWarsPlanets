package ir.iact.starwarsplanets.data.repository

import ir.iact.starwarsplanets.data.remote.PlanetApiService
import ir.iact.starwarsplanets.domain.model.Planet

class PlanetRepositoryImpl(
    private val planetApiService: PlanetApiService
) : PlanetRepository {

    override suspend fun getPlanets(): List<Planet> {
        return emptyList()
    }
}