package com.example.starwarsdocs.domain.useCase

import androidx.room.Insert
import com.example.starwarsdocs.data.local.LocalDatabase
import com.example.starwarsdocs.data.local.models.StarShipData
import com.example.starwarsdocs.util.WrapperResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertLocalStarshipUseCase @Inject constructor(
    private val localDatabase: LocalDatabase
) {
    suspend fun insertStarship(starShipData: StarShipData): WrapperResponse<Unit>{
        val response = try {
            localDatabase.dao().insertStarShipData(starShipData)
        }catch (e: Exception){
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }
}