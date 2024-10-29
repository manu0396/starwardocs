package com.example.starwarsdocs.domain.models

data class StarShipDomain(
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val cost_in_credits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperdrive_rating: String,
    val lenght: String,
    val manufacturer: String,
    val max_atmosphering_speed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: String,
    val startship_class: String,
    val url: String
)