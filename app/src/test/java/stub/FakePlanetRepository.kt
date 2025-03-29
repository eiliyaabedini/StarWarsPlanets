package stub

import ir.iact.starwarsplanets.data.repository.PlanetRepository
import ir.iact.starwarsplanets.domain.model.Planet

class FakePlanetRepository : PlanetRepository {

    private var throwError = false
    private var planets: MutableList<Planet> = mutableListOf()

    override suspend fun getPlanets(): List<Planet> {
        if (throwError) {
            throw Exception("Error getting planets")
        }
        return planets.toList()
    }

    override suspend fun getPlanet(name: String): Planet {
        if (throwError) {
            throw Exception("Error getting planet")
        }
        return planets.find { it.name == name }
            ?: throw Exception("Planet not found")
    }

    fun setPlanets(newPlanets: List<Planet>) {
        planets = newPlanets.toMutableList()
    }

    fun setThrowError(value: Boolean) {
        throwError = value
    }
}