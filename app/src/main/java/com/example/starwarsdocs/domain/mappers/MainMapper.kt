package com.example.starwarsdocs.domain.mappers

import com.example.starwarsdocs.data.local.models.PeopleData
import com.example.starwarsdocs.data.remote.models.ApiPeopleResponseDTO
import com.example.starwarsdocs.data.remote.models.People
import com.example.starwarsdocs.domain.models.ApiPeopleResponseDomain
import com.example.starwarsdocs.domain.models.PeopleDomain

object MainMapper {

    fun apiPeopleResponseDTOToDomain(input:ApiPeopleResponseDTO):ApiPeopleResponseDomain{
        return ApiPeopleResponseDomain(
            count = input.count,
            next = input.next,
            previous = apiPeopleToPeopleDomain(input.previous),
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
}