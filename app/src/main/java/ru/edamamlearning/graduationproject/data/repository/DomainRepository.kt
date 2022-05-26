package ru.edamamlearning.graduationproject.data.repository

import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.domain.room.FoodEntity

interface DomainRepository {

    suspend fun getFoodModel(text: String): List<FoodDomainModel>
    suspend fun getSavedFood(): List<FoodEntity>
}