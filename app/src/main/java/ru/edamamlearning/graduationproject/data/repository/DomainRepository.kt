package ru.edamamlearning.graduationproject.data.repository

import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface DomainRepository {

    suspend fun getFoodModel(text: String): List<FoodDomainModel>
}