package ir.iact.starwarsplanets.data.local

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import stub.PlanetData

@ExperimentalCoroutinesApi
class PlanetCacheTest {

    private lateinit var planetCache: PlanetCache

    @Before
    fun setup() {
        planetCache = PlanetCache()
    }

    @Test
    fun `WHEN getPlanets THEN return planets from cache`() {
        planetCache.setPlanets(PlanetData.planetList)

        val planets = planetCache.getPlanets()

        assertEquals(3, planets.size)
        assertTrue(planets.containsAll(PlanetData.planetList))
    }

    @Test
    fun `GIVEN planet exist in the list WHEN getPlanet THEN return planets from cache`() {
        planetCache.setPlanets(PlanetData.planetList)

        val planet = planetCache.getPlanet(PlanetData.planetTatooine.name)

        assertEquals(planet, PlanetData.planetTatooine)
    }

    @Test
    fun `GIVEN planet does not exist in the list WHEN getPlanet THEN return null`() {
        planetCache.setPlanets(PlanetData.planetList)

        val planet = planetCache.getPlanet(PlanetData.planetEarth.name)

        assertNull(planet)
    }
}