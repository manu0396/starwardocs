package com.example.starwarsdocs.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Planets(
    @SerialName(value = "climate") val climate: String,
    @SerialName(value = "created") val created: String,
    @SerialName(value = "diameter") val diameter: String,
    @SerialName(value = "edited") val edited: String,
    @SerialName(value = "films") val films: List<String>,
    @SerialName(value = "gravity") val gravity: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "orbital_period") val orbital_period: String,
    @SerialName(value = "population") val population: String,
    @SerialName(value = "residents") val residents: List<String>,
    @SerialName(value = "rotation_period") val rotation_period: String,
    @SerialName(value = "surface_water") val surface_water: String,
    @SerialName(value = "terrain") val terrain: String,
    @SerialName(value = "url") val url: String
)