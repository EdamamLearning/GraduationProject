package ru.edamamlearning.graduationproject.room

import ru.edamamlearning.graduationproject.data.repository.CacheFoodRepository
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity
import javax.inject.Inject

class CacheFoodRepositoryImpl @Inject constructor(
    private val database: FoodDataBase
) : CacheFoodRepository {

    /**
     * Вставить список продуктов в таблицу БД
     */
    override suspend fun insertListFood(food: List<HistoryFoodEntity>) {
        database.foodDao().insertAll(food)
    }
}