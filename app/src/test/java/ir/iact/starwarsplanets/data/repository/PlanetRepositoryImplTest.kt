package ir.iact.starwarsplanets.data.repository

import ir.iact.starwarsplanets.data.local.PlanetCache
import ir.iact.starwarsplanets.data.remote.model.PlanetDto
import ir.iact.starwarsplanets.domain.model.Planet
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import stub.FakePlanetApiService

class PlanetRepositoryImplTest {

    private val planetApiService = FakePlanetApiService()
    private val planetCache = PlanetCache()

    private val repository = PlanetRepositoryImpl(planetApiService, planetCache)

    private val planetDtoList = listOf(
        PlanetDto(
            name = "Tatooine",
            climate = "Temperate",
            population = "1000000",
            diameter = "10465",
            gravity = "1 standard",
            terrain = "desert",
            created = "2014-12-09T13:50:49.641000Z"

        ),
        PlanetDto(
            name = "Alderaan",
            climate = "Temperate",
            population = "1000000",
            diameter = "12500",
            gravity = "1 standard",
            terrain = "grasslands, mountains",
            created = "2014-12-09T13:50:49.641000Z"
        ),
        PlanetDto(
            name = "Hoth",
            climate = "frozen",
            population = "unknown",
            diameter = "7200",
            gravity = "1.1 standard",
            terrain = "tundra, ice caves, mountain ranges",
            created = "2014-12-10T11:39:13.934000Z"
        )
    )

    private val planetDomainList = listOf(
        Planet(
            name = "Tatooine",
            climate = "Temperate",
            population = 1000000,
            diameter = 10465,
            gravity = "1 standard",
            terrain = "desert"
        ),
        Planet(
            name = "Alderaan",
            climate = "Temperate",
            population = 1000000,
            diameter = 12500,
            gravity = "1 standard",
            terrain = "grasslands, mountains"
        ),
        Planet(
            name = "Hoth",
            climate = "frozen",
            population = null,
            diameter = 7200,
            gravity = "1.1 standard",
            terrain = "tundra, ice caves, mountain ranges"
        )
    )

    @Test
    fun `WHEN getPlanets THEN map to domain model and return planets from api service`() = runTest {
        planetApiService.setPlanets(planetDtoList)

        val planets = repository.getPlanets()

        assertEquals(planets.size, planetDtoList.size)
        assertEquals(planetDomainList, planets)
    }

    @Test(expected = Exception::class)
    fun `WHEN api service throw error THEN repository should throw error`() = runTest {
        planetApiService.setThrowError(true)
        repository.getPlanets()
    }

    @Test
    fun `WHEN getPlanet THEN return planet from local cache`() = runTest {
        planetCache.setPlanets(planetDomainList)

        val planetResult = repository.getPlanet("Tatooine")

        assertEquals(planetResult, planetDomainList[0])
    }

    @Test(expected = Exception::class)
    fun `GIVEN planet list is empty WHEN getPlanet THEN throw error`() = runTest {
        repository.getPlanet("Tatooine")
    }

    @Test
    fun `WHEN getPlanets THEN save result to local cache`() = runTest {
        planetApiService.setPlanets(planetDtoList)

        repository.getPlanets()

        assertEquals(planetCache.getPlanets(), planetDomainList)
    }
}