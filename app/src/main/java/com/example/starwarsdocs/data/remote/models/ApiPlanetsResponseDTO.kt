package com.example.starwarsdocs.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiPlanetsResponseDTO(
    @SerialName(value = "count") val count: Int,
    @SerialName(value = "next") val next: String,
    @SerialName(value = "previous") val previous: Planets,
    @SerialName(value = "results") val results: List<Planets>
)