package com.example.starwarsdocs.domain.models

import com.example.starwarsdocs.data.remote.models.People

data class ApiPeopleResponseDomain(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PeopleDomain>
)