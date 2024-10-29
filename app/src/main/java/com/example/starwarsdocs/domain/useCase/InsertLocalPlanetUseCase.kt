package com.example.starwarsdocs.domain.useCase

import com.example.starwarsdocs.data.local.LocalDatabase
import com.example.starwarsdocs.data.local.models.PlanetData
import com.example.starwarsdocs.data.remote.models.Planets
import com.example.starwarsdocs.util.WrapperResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertLocalPlanetUseCase @Inject constructor(
    private val localDatabase: LocalDatabase
) {

    suspend fun insertPlanet(planets: PlanetData): WrapperResponse<Unit>{
        val response = try {
            localDatabase.dao().insertPlanetData(planets)
        }catch (e:Exception){
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }

}