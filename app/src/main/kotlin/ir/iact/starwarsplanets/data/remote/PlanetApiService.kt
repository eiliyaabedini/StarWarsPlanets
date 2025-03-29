package ir.iact.starwarsplanets.data.remote

import ir.iact.starwarsplanets.data.remote.model.PlanetResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApiService {
    @GET("planets")
    suspend fun getPlanets(@Query("page") page: Int = 1): PlanetResponseDto
}