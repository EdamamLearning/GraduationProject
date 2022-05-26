package ru.edamamlearning.graduationproject.data.repository

import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity

interface CacheFoodRepository {

    /**
     * Вставить список продуктов в таблицу БД
     */
    suspend fun insertListFood(food: List<HistoryFoodEntity>)
}