package com.example.starwarsdocs.data.remote

import com.example.starwarsdocs.data.remote.models.ApiPeopleResponseDTO
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people/")
    suspend fun getAllPeople(@Query("page") page: Int): ApiPeopleResponseDTO

}