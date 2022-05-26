package ru.edamamlearning.graduationproject.domain.room

import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface CacheFoodRepository {

    /**
     * Вставить список продуктов в таблицу БД
     */
    suspend fun insertListFood(food: List<FoodDomainModel>)
}