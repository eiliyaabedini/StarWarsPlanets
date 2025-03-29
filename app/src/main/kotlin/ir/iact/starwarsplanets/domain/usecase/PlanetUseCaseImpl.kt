package ir.iact.starwarsplanets.domain.usecase

import ir.iact.starwarsplanets.data.repository.PlanetRepository
import ir.iact.starwarsplanets.domain.model.Planet
import javax.inject.Inject

class PlanetUseCaseImpl @Inject constructor(
    private val planetRepository: PlanetRepository
) : PlanetUseCase {
    override suspend fun getPlanets(): List<Planet> {
        return planetRepository.getPlanets()
    }

    override suspend fun getPlanet(name: String): Planet {
        TODO("Not yet implemented")
    }
}