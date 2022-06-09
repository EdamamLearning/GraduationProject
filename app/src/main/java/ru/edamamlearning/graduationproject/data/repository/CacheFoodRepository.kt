package ru.edamamlearning.graduationproject.data.repository

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.room.entity.DiaryFoodEntity
import ru.edamamlearning.graduationproject.room.entity.FavoriteFoodEntity
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity

interface CacheFoodRepository {

    suspend fun saveSearchedFood(food: List<HistoryFoodEntity>)
    suspend fun saveFavoriteFood(food: FavoriteFoodEntity)
    suspend fun saveDiaryFood(food: DiaryFoodEntity)
    suspend fun deleteFavoriteFood(food: FavoriteFoodEntity)
    suspend fun deleteDiaryFood(food: DiaryFoodEntity)
    suspend fun getAllHistoryFoods(): List<HistoryFoodEntity>
    suspend fun getFoodModelById(foodId: String): HistoryFoodEntity
    fun getAllFavoriteFoods(): Flow<List<FavoriteFoodEntity>>
    fun getAllDiaryFoods(): Flow<List<DiaryFoodEntity>>
}