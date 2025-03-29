package ir.iact.starwarsplanets.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    val climate: String,
    val population: Long?
)