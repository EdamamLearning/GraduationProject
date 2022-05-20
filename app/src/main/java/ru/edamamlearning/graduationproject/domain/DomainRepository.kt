package ru.edamamlearning.graduationproject.domain

import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface DomainRepository {

    suspend fun get(text: String): List<FoodDomainModel>
}