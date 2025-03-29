package ir.iact.starwarsplanets.domain.usecase

import ir.iact.starwarsplanets.domain.model.Planet
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import stub.FakePlanetRepository

class PlanetUseCaseImplTest {
    private val planetRepository = FakePlanetRepository()
    private val planetUseCase = PlanetUseCaseImpl(planetRepository)


    @Test
    fun `WHEN getPlanets THEN return planets from repository`() = runTest {
        val expectedPlanets = listOf(
            Planet(name = "Tatooine", population = 200000L, climate = "arid"),
            Planet(name = "Alderaan", population = 2000000000L, climate = "temperate"),
            Planet(name = "Yavin IV", population = 1000L, climate = "tropical, temperate")
        )
        planetRepository.setPlanets(expectedPlanets)

        val actualPlanets = planetUseCase.getPlanets()

        assertEquals(expectedPlanets, actualPlanets)
    }

    @Test(expected = Exception::class)
    fun `WHEN repository throw error THEN usecase should throw error`() = runTest {
        planetRepository.setThrowError(true)
        planetUseCase.getPlanets()
    }
}



