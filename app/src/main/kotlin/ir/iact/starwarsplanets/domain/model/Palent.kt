package ir.iact.starwarsplanets.domain.model

data class Planet(
    val name: String,
    val climate: String,
    val population: Long?
)