package stub

import ir.iact.starwarsplanets.domain.model.Planet

object PlanetData {

    val planetEarth = Planet(
        name = "Earth",
        climate = "Tropical",
        population = 7_000_000_000,
        diameter = 12_756,
        gravity = "1 standard",
        terrain = "grasslands, mountains"
    )

    val planetTatooine = Planet(
        name = "Tatooine",
        population = 200000L,
        climate = "arid",
        diameter = 10465,
        gravity = "1 standard",
        terrain = "desert"
    )

    val planetsMap = mapOf(
        "Earth" to planetEarth,
        "Mars" to Planet(
            name = "Mars",
            climate = "Cold",
            population = 0,
            diameter = 6_792,
            gravity = "0.3 standard",
            terrain = "deserts"
        ),
        "Jupiter" to Planet(
            name = "Jupiter",
            climate = "Hot",
            population = 1_000_000_000,
            diameter = 142_984,
            gravity = "2.5 standard",
            terrain = "gas giant"
        )
    )

    val planetList = listOf(
        planetTatooine,
        Planet(
            name = "Alderaan",
            population = 2000000000L,
            climate = "temperate",
            diameter = 12500,
            gravity = "1 standard",
            terrain = "grasslands, mountains"
        ),
        Planet(
            name = "Yavin IV",
            population = 1000L,
            climate = "tropical, temperate",
            diameter = 10200,
            gravity = "1 standard",
            terrain = "jungle, rainforests"
        )
    )
}