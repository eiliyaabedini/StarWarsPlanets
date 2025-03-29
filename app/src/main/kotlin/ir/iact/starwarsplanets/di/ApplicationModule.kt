package ir.iact.starwarsplanets.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.iact.starwarsplanets.data.remote.PlanetApiService
import ir.iact.starwarsplanets.data.repository.PlanetRepository
import ir.iact.starwarsplanets.data.repository.PlanetRepositoryImpl
import ir.iact.starwarsplanets.domain.usecase.PlanetUseCase
import ir.iact.starwarsplanets.domain.usecase.PlanetUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val ENDPOINT = "https://swapi.dev/api/"


@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindPlanetRepository(impl: PlanetRepositoryImpl): PlanetRepository

    @Binds
    @Singleton
    abstract fun bindPlanetUseCase(impl: PlanetUseCaseImpl): PlanetUseCase

    companion object {
        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        @Provides
        @Singleton
        fun providePlanetApi(
            okHttpClient: OkHttpClient
        ): PlanetApiService {
            return Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(PlanetApiService::class.java)
        }
    }
}