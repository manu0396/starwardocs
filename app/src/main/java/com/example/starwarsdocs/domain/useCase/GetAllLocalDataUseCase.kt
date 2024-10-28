package com.example.starwarsdocs.domain.useCase

import com.example.starwarsdocs.data.local.LocalDatabase
import com.example.starwarsdocs.data.local.models.PeopleData
import com.example.starwarsdocs.util.WrapperResponse
import javax.inject.Inject

class GetAllLocalDataUseCase @Inject constructor(
    private val localDatabase: LocalDatabase
) {

    fun getAllLocalCharacter():WrapperResponse<List<PeopleData>>{
        val response = try {
            localDatabase.dao().getAllLocalCharacters()
        }catch (e: Exception){
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }
}