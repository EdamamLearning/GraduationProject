package ru.edamamlearning.graduationproject.room

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.data.repository.CacheFoodRepository
import ru.edamamlearning.graduationproject.room.entity.FavoriteFoodEntity
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity
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

    override suspend fun deleteFavoriteFood(food: FavoriteFoodEntity) {
        database.favoriteFoodDao().delete(food)
    }

    override fun getAllFavoriteFoods(): Flow<List<FavoriteFoodEntity>> {
        return database.favoriteFoodDao().getAll()
    }
}