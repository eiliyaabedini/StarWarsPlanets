package ir.iact.starwarsplanets.data.local

import ir.iact.starwarsplanets.domain.model.Planet
import javax.inject.Inject

class PlanetCache @Inject constructor() {
    private val planets = mutableListOf<Planet>()

    fun getPlanets(): List<Planet> {
        return planets.toList()
    }

    fun setPlanets(planets: List<Planet>) {
        this.planets.clear()
        this.planets.addAll(planets)
    }

    fun getPlanet(name: String): Planet? {
        return planets.find { it.name == name }
    }
}