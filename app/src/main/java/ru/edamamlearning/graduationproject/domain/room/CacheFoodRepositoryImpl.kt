package ru.edamamlearning.graduationproject.domain.room

import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import javax.inject.Inject

class CacheFoodRepositoryImpl @Inject constructor(
    private val database: FoodDataBase
) : CacheFoodRepository {

    /**
     * Вставить список продуктов в таблицу БД
     */
    override suspend fun insertListFood(food: List<FoodDomainModel>) {
        val foodEntity = food.map(FoodEntity.Mapper::map)
        database.foodDao().insertAll(foodEntity)
    }
}