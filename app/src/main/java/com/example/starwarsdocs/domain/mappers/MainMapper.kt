package com.example.starwarsdocs.domain.mappers

import com.example.starwarsdocs.data.local.models.PeopleData
import com.example.starwarsdocs.data.local.models.PlanetData
import com.example.starwarsdocs.data.local.models.StarShipData
import com.example.starwarsdocs.data.remote.models.ApiPeopleResponseDTO
import com.example.starwarsdocs.data.remote.models.People
import com.example.starwarsdocs.data.remote.models.Planets
import com.example.starwarsdocs.data.remote.models.Starships
import com.example.starwarsdocs.domain.models.ApiPeopleResponseDomain
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.domain.models.PlanetsDomain
import com.example.starwarsdocs.domain.models.StarShipDomain
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object MainMapper {

    fun apiPeopleResponseDTOToDomain(input:ApiPeopleResponseDTO):ApiPeopleResponseDomain{
        return ApiPeopleResponseDomain(
            count = input.count,
            next = input.next,
            previous = input.previous,
            results = input.results.map { apiPeopleToPeopleDomain(it) }
        )
    }

    fun apiPeopleToPeopleDomain(input: People): PeopleDomain{
        return PeopleDomain(
            birth_year = input.birth_year,
            created = input.created,
            edited = input.edited,
            eye_color = input.eye_color,
            films = input.films,
            gender = input.gender,
            hair_color = input.hair_color,
            height = input.height,
            homeworld = input.homeworld,
            mass = input.mass,
            name = input.name,
            skin_color = input.skin_color,
            species = input.species,
            starships = input.starships,
            url = input.url,
            vehicles = input.vehicles
        )
    }
    fun PeopleData.toDomain(): PeopleDomain {
        return PeopleDomain(
            birth_year = this.birth_year,
            created = this.created,
            edited = this.edited,
            eye_color = this.eye_color,
            films = this.films,
            gender = this.gender,
            hair_color = this.hair_color,
            height = this.height,
            homeworld = this.homeworld,
            mass = this.mass,
            name = this.name,
            skin_color = this.skin_color,
            species = this.species,
            starships = this.starships,
            url = this.url,
            vehicles = this.vehicles
        )
    }
    fun PeopleDomain.toData(): PeopleData {
        return PeopleData(
            id = 0, // assuming auto-generated ID, set 0 or ignore in case of insertion
            birth_year = this.birth_year,
            created = this.created,
            edited = this.edited,
            eye_color = this.eye_color,
            films = this.films,
            gender = this.gender,
            hair_color = this.hair_color,
            height = this.height,
            homeworld = this.homeworld,
            mass = this.mass,
            name = this.name,
            skin_color = this.skin_color,
            species = this.species,
            starships = this.starships,
            url = this.url,
            vehicles = this.vehicles
        )
    }

    fun Planets.toDomain(): PlanetsDomain {
        return PlanetsDomain(
            climate = this.climate,
            create = this.created,  // Map 'created' to 'create'
            diameter = this.diameter,
            edited = this.edited,
            films = this.films,
            gravity = this.gravity,
            name = this.name,
            orbital_period = this.orbital_period,
            population = this.population,
            residents = this.residents,
            rotation_period = this.rotation_period,
            surface_water = this.surface_water,
            terrain = this.terrain,
            url = this.url
        )
    }

    fun Starships.toDomain(): StarShipDomain {
        return StarShipDomain(
            MGLT = this.MGLT,
            cargo_capacity = this.cargo_capacity,
            consumables = this.consumables,
            cost_in_credits = this.cost_in_credits,
            created = this.created,
            crew = this.crew,
            edited = this.edited,
            films = this.films,
            hyperdrive_rating = this.hyperdrive_rating,
            lenght = this.length,  // Note: correct any typos in length if needed
            manufacturer = this.manufacturer,
            max_atmosphering_speed = this.max_atmosphering_speed,
            model = this.model,
            name = this.name,
            passengers = this.passengers,
            pilots = this.pilots.joinToString(", "),  // Join list into a single string
            startship_class = this.starship_class,  // Note: correct typos if needed
            url = this.url
        )
    }

    fun PlanetsDomain.toDataModel(id: Int = 0): PlanetData {
        return PlanetData(
            id = id,  // Default to 0 if a new record, otherwise set from existing data
            climate = this.climate,
            create = this.create,
            diameter = this.diameter,
            edited = this.edited,
            films = this.films,
            gravity = this.gravity,
            name = this.name,
            orbital_period = this.orbital_period,
            population = this.population,
            residents = Json.encodeToString(this.residents),  // Convert List<String> to JSON String
            rotation_period = this.rotation_period,
            surface_water = this.surface_water,
            terrain = this.terrain,
            url = this.url
        )
    }

    fun PlanetData.toDomainModel(): PlanetsDomain {
        return PlanetsDomain(
            climate = this.climate,
            create = this.create,
            diameter = this.diameter,
            edited = this.edited,
            films = this.films,
            gravity = this.gravity,
            name = this.name,
            orbital_period = this.orbital_period,
            population = this.population,
            residents = Json.decodeFromString(this.residents),  // Convert JSON String back to List<String>
            rotation_period = this.rotation_period,
            surface_water = this.surface_water,
            terrain = this.terrain,
            url = this.url
        )
    }

    fun StarShipDomain.toDataModel(id: Int = 0): StarShipData {
        return StarShipData(
            id = id,  // Default to 0 for new entries
            MGLT = this.MGLT,
            cargo_capacity = this.cargo_capacity,
            consumables = this.consumables,
            cost_in_credits = this.cost_in_credits,
            created = this.created,
            crew = this.crew,
            edited = this.edited,
            films = this.films,  // Assuming List<String> is supported, otherwise serialize
            hyperdrive_rating = this.hyperdrive_rating,
            length = this.lenght,  // Map "lenght" to "length"
            manufacturer = this.manufacturer,
            max_atmosphering_speed = this.max_atmosphering_speed,
            model = this.model,
            name = this.name,
            passengers = this.passengers,
            pilots = this.pilots,  // Assuming String format in StarShipDomain
            starship_class = this.startship_class,  // Map "startship_class" to "starship_class"
            url = this.url
        )
    }

    fun StarShipData.toDomainModel(): StarShipDomain {
        return StarShipDomain(
            MGLT = this.MGLT,
            cargo_capacity = this.cargo_capacity,
            consumables = this.consumables,
            cost_in_credits = this.cost_in_credits,
            created = this.created,
            crew = this.crew,
            edited = this.edited,
            films = this.films,  // Assuming List<String> is already available
            hyperdrive_rating = this.hyperdrive_rating,
            lenght = this.length,  // Map "length" to "lenght"
            manufacturer = this.manufacturer,
            max_atmosphering_speed = this.max_atmosphering_speed,
            model = this.model,
            name = this.name,
            passengers = this.passengers,
            pilots = this.pilots,  // Assuming String format in StarShipData
            startship_class = this.starship_class,  // Map "starship_class" to "startship_class"
            url = this.url
        )
    }
}