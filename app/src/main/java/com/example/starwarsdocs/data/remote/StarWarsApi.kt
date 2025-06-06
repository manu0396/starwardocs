package com.example.starwarsdocs.data.remote

import com.example.starwarsdocs.data.remote.models.ApiPeopleResponseDTO
import com.example.starwarsdocs.data.remote.models.ApiPlanetsResponseDTO
import com.example.starwarsdocs.data.remote.models.ApiStartshipsResponseDTO
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun getAllPeople(@Query("page") page: Int): ApiPeopleResponseDTO

    @GET("planets/")
    suspend fun getAllPlanets(@Query("page") page: Int): ApiPlanetsResponseDTO

    @GET("starships/")
    suspend fun getAllStarships(@Query("page") page: Int): ApiStartshipsResponseDTO
}