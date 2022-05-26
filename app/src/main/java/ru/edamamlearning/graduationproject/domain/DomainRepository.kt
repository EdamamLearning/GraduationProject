package ru.edamamlearning.graduationproject.domain

import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface DomainRepository {

    suspend fun getFoodModel(text: String): List<FoodDomainModel>
    suspend fun saveSearchingHistory(list: List<FoodDomainModel>)
}