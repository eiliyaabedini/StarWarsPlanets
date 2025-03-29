package ir.iact.starwarsplanets.presentation

import ir.iact.starwarsplanets.domain.model.Planet

object PlanetData {
    val planet = Planet(
        name = "Earth",
        climate = "Tropical",
        population = 7_000_000_000,
        diameter = 10465,
        gravity = "1 standard",
        terrain = "desert"
    )
    val planet2 = Planet(
        name = "Mars",
        climate = "Cold",
        population = 0,
        diameter = 10465,
        gravity = "1 standard",
        terrain = "desert"
    )

    val planet3 = Planet(
        name = "Tatooine",
        climate = "arid",
        population = 200000,
        diameter = 10465,
        gravity = "1 standard",
        terrain = "desert"
    )
}