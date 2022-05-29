package ru.edamamlearning.graduationproject.domain

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface DomainRepository {

    suspend fun getFoodModel(text: String): List<FoodDomainModel>
    suspend fun getFoodModelById(foodId: String): FoodDomainModel
    suspend fun saveFavoriteFood(foodDomainModel: FoodDomainModel)
    suspend fun deleteFavoriteFood(foodDomainModel: FoodDomainModel)
    suspend fun getAllHistoryFoods(): List<FoodDomainModel>
    fun getAllFavoriteFoods(): Flow<List<FoodDomainModel>>
}