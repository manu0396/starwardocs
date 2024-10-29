package com.example.starwarsdocs.domain.useCase

import com.example.starwarsdocs.data.remote.StarWarsApi
import com.example.starwarsdocs.domain.mappers.MainMapper.toDomain
import com.example.starwarsdocs.domain.models.StarShipDomain
import com.example.starwarsdocs.util.WrapperResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllStarShipUseCase @Inject constructor(
    private val api: StarWarsApi
){
    suspend fun getAllStarShip(page:Int): WrapperResponse<List<StarShipDomain>>{
        val response = try {
            api.getAllStarships(page).results.map {
                it.toDomain()
            }
        }catch (e:Exception){
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }

}