package ir.iact.starwarsplanets.domain.usecase

import ir.iact.starwarsplanets.domain.model.Planet

interface PlanetUseCase {
    suspend fun getPlanets(): List<Planet>

    suspend fun getPlanet(name: String): Planet
}