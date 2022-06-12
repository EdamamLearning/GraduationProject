package ru.edamamlearning.graduationproject.room

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.data.repository.CacheFoodRepository
import ru.edamamlearning.graduationproject.room.entity.DiaryFoodEntity
import ru.edamamlearning.graduationproject.room.entity.FavoriteFoodEntity
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity
import ru.edamamlearning.graduationproject.room.entity.InfoFoodEntity
import javax.inject.Inject

class CacheFoodRepositoryImpl @Inject constructor(
    private val database: FoodDataBase
) : CacheFoodRepository {

    /**
     * Вставить список продуктов в таблицу БД
     */
    override suspend fun saveSearchedFood(food: List<HistoryFoodEntity>) {
        database.historyFoodDao().insertAll(food)
    }

    override suspend fun saveFavoriteFood(food: FavoriteFoodEntity) {
        database.favoriteFoodDao().insert(food)
    }

    override suspend fun saveInfoFood(food: InfoFoodEntity) {
        database.infoFoodDao().insert(food)
    }

    override suspend fun deleteInfoFood(food: InfoFoodEntity) {
        database.infoFoodDao().delete(food)
    }

    override fun getAllInfoFood(): Flow<List<InfoFoodEntity>> {
        return database.infoFoodDao().getAll()
    }

    override suspend fun saveDiaryFood(food: DiaryFoodEntity) {
        database.diaryFoodDao().insert(food)
    }

    override suspend fun deleteDiaryFood(food: DiaryFoodEntity) {
        database.diaryFoodDao().delete(food)
    }

    override suspend fun deleteFavoriteFood(food: FavoriteFoodEntity) {
        database.favoriteFoodDao().delete(food)
    }

    override suspend fun getAllHistoryFoods(): List<HistoryFoodEntity> {
        return database.historyFoodDao().getAll()
    }

    override suspend fun getFoodModelById(foodId: String): HistoryFoodEntity {
        return database.historyFoodDao().getDataByFoodId(foodId)
    }

    override fun getAllFavoriteFoods(): Flow<List<FavoriteFoodEntity>> {
        return database.favoriteFoodDao().getAll()
    }

    override fun getAllDiaryFoods(): Flow<List<DiaryFoodEntity>> {
        return database.diaryFoodDao().getAll()
    }

    override suspend fun getDiaryFoodsByDate(date: String): List<DiaryFoodEntity> {
        return database.diaryFoodDao().getByDate(date)
    }
}