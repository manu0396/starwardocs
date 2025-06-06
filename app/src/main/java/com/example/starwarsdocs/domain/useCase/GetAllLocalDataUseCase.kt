package com.example.starwarsdocs.domain.useCase

import com.example.starwarsdocs.data.local.LocalDatabase
import com.example.starwarsdocs.data.local.models.PeopleData
import com.example.starwarsdocs.data.local.models.PlanetData
import com.example.starwarsdocs.data.local.models.StarShipData
import com.example.starwarsdocs.util.WrapperResponse
import javax.inject.Inject

class GetAllLocalDataUseCase @Inject constructor(
    private val localDatabase: LocalDatabase
) {

    fun getAllLocalCharacter(): WrapperResponse<List<PeopleData>> {
        val response = try {
            localDatabase.dao().getAllLocalCharacters()
        } catch (e: Exception) {
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }

    fun getAllLocalStarships(): WrapperResponse<List<StarShipData>> {
        val response = try {
            localDatabase.dao().getAllLocalStarship()
        } catch (e: Exception) {
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }

    fun getAllLocalPlanets(): WrapperResponse<List<PlanetData>> {
        val response = try {
            localDatabase.dao().getAllLocalPlanets()
        } catch (e: Exception) {
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }
}