package ru.edamamlearning.graduationproject.domain

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.domain.model.DiaryFoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface DomainRepository {

    suspend fun getFoodModel(text: String): List<FoodDomainModel>
    suspend fun getFoodModelById(foodId: String): FoodDomainModel
    suspend fun saveInfoFood(foodDomainModel: FoodDomainModel)
    suspend fun deleteInfoFood(foodDomainModel: FoodDomainModel)
    suspend fun saveFavoriteFood(foodDomainModel: FoodDomainModel)
    suspend fun saveDiaryFood(diaryFoodDomainModel: DiaryFoodDomainModel)
    suspend fun deleteFavoriteFood(foodDomainModel: FoodDomainModel)
    suspend fun deleteDiaryFood(diaryFoodDomainModel: DiaryFoodDomainModel)
    suspend fun getAllHistoryFoods(): List<FoodDomainModel>
    fun getAllFavoriteFoods(): Flow<List<FoodDomainModel>>

    fun getAllInfoFoods(): Flow<List<FoodDomainModel>>

    fun getAllDiaryFoods(): Flow<List<DiaryFoodDomainModel>>
    fun getDiaryFoodsByDate(date: String): Flow<List<DiaryFoodDomainModel>>
}