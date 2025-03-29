package ir.iact.starwarsplanets.domain.usecase

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import stub.FakePlanetRepository
import stub.PlanetData

class PlanetUseCaseImplTest {

    private val planetRepository = FakePlanetRepository()
    private val planetUseCase = PlanetUseCaseImpl(planetRepository)

    @Test
    fun `WHEN getPlanets THEN return planets from repository`() = runTest {
        planetRepository.setPlanets(PlanetData.planetList)

        val actualPlanets = planetUseCase.getPlanets()

        assertEquals(PlanetData.planetList, actualPlanets)
    }

    @Test(expected = Exception::class)
    fun `WHEN repository throw error THEN usecase should throw error`() = runTest {
        planetRepository.setThrowError(true)
        planetUseCase.getPlanets()
    }

    @Test
    fun `WHEN getPlanet THEN return planet from repository`() = runTest {
        planetRepository.setPlanets(listOf(PlanetData.planetTatooine))

        val actualPlanet = planetUseCase.getPlanet("Tatooine")

        assertEquals(PlanetData.planetTatooine, actualPlanet)
    }

    @Test(expected = Exception::class)
    fun `WHEN getPlanet throw error THEN usecase should throw error`() = runTest {
        planetRepository.setThrowError(true)
        planetUseCase.getPlanet("any")
    }
}



