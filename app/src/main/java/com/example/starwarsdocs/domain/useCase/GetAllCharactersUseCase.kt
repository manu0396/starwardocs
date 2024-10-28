package com.example.starwarsdocs.domain.useCase

import com.example.starwarsdocs.data.remote.StarWarsApi
import com.example.starwarsdocs.data.remote.models.ApiPeopleResponseDTO
import com.example.starwarsdocs.domain.mappers.MainMapper
import com.example.starwarsdocs.domain.models.ApiPeopleResponseDomain
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.util.WrapperResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCharactersUseCase @Inject constructor(
    private val api: StarWarsApi
) {

    suspend fun getAllCharacters(page:Int): WrapperResponse<List<PeopleDomain>>{
        val response = try {
            api.getAllPeople(page).results.map {
                MainMapper.apiPeopleToPeopleDomain(it)
            }
        }catch (e:Exception){
            return WrapperResponse.Error(e.message ?: "Se ha producido un error")
        }
        return WrapperResponse.Success(response)
    }
}