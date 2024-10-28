package com.example.starwarsdocs.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class People(
    @SerialName(value = "birth_year") val birth_year: String,
    @SerialName(value = "created") val created: String,
    @SerialName(value = "edited") val edited: String,
    @SerialName(value = "eye_color") val eye_color: String,
    @SerialName(value = "films") val films: List<String>,
    @SerialName(value = "gender") val gender: String,
    @SerialName(value = "hair_color") val hair_color: String,
    @SerialName(value = "height") val height: String,
    @SerialName(value = "homeworld") val homeworld: String,
    @SerialName(value = "mass") val mass: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "skin_color") val skin_color: String,
    @SerialName(value = "species") val species: List<String>,
    @SerialName(value = "starships") val starships: List<String>,
    @SerialName(value = "url") val url: String,
    @SerialName(value = "vehicles") val vehicles: List<String>
)