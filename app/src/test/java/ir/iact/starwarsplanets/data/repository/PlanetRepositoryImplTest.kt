package ir.iact.starwarsplanets.data.repository

import ir.iact.starwarsplanets.data.remote.model.PlanetDto
import ir.iact.starwarsplanets.domain.model.Planet
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import stub.FakePlanetApiService

class PlanetRepositoryImplTest {

    private val planetApiService = FakePlanetApiService()

    private val repository = PlanetRepositoryImpl(planetApiService)

    private val planetDtoList = listOf(
        PlanetDto(
            name = "Planet1",
            climate = "Temperate",
            population = "1000000",
            created = "2014-12-09T13:50:49.641000Z"
        ),
        PlanetDto(
            name = "Planet2",
            climate = "Temperate",
            population = "1000000",
            created = "2014-12-09T13:50:49.641000Z"
        )
    )

    private val planetDomainList = listOf(
        Planet(
            name = "Planet1",
            climate = "Temperate",
            population = 1000000
        ),
        Planet(
            name = "Planet2",
            climate = "Temperate",
            population = 1000000
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
}