package ru.edamamlearning.graduationproject.domain

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface DomainRepository {

    suspend fun getFoodModel(text: String): List<FoodDomainModel>
    suspend fun saveFavoriteFood(foodDomainModel: FoodDomainModel)
    suspend fun deleteFavoriteFood(foodDomainModel: FoodDomainModel)
    fun getAllFavoriteFoods(): Flow<List<FoodDomainModel>>
}