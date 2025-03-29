package ir.iact.starwarsplanets.data.remote.model

import com.google.gson.annotations.SerializedName

data class PlanetResponseDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PlanetDto>
)

data class PlanetDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("climate")
    val climate: String,
    @SerializedName("population")
    val population: String,
    @SerializedName("diameter")
    val diameter: String,
    @SerializedName("terrain")
    val terrain: String,
    @SerializedName("gravity")
    val gravity: String,
    @SerializedName("created")
    val created: String,
)