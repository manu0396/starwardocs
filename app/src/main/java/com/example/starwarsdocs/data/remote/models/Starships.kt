package com.example.starwarsdocs.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Starships(
    @SerialName(value = "MGLT") val MGLT: String,
    @SerialName(value = "cargo_capacity") val cargo_capacity: String,
    @SerialName(value = "consumables") val consumables: String,
    @SerialName(value = "cost_in_credits") val cost_in_credits: String,
    @SerialName(value = "created") val created: String,
    @SerialName(value = "crew") val crew: String,
    @SerialName(value = "edited") val edited: String,
    @SerialName(value = "films") val films: List<String>,
    @SerialName(value = "hyperdrive_rating") val hyperdrive_rating: String,
    @SerialName(value = "length") val length: String,
    @SerialName(value = "manufacturer") val manufacturer: String,
    @SerialName(value = "max_atmosphering_speed") val max_atmosphering_speed: String,
    @SerialName(value = "model") val model: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "passengers") val passengers: String,
    @SerialName(value = "pilots") val pilots: List<String>,
    @SerialName(value = "starship_class") val starship_class: String,
    @SerialName(value = "url") val url: String
)