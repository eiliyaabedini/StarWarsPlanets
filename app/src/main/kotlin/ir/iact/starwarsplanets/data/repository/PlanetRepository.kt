package ir.iact.starwarsplanets.data.repository

import ir.iact.starwarsplanets.domain.model.Planet

interface PlanetRepository {
    suspend fun getPlanets(): List<Planet>
}