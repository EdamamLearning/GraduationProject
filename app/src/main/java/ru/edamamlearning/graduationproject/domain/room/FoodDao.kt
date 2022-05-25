package ru.edamamlearning.graduationproject.domain.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {

    // Получить весь список продуктов
    @Query("SELECT * FROM food_table")
    suspend fun getAll(): List<FoodEntity>

    // Получить конкретный продукт
    @Query("SELECT * FROM food_table WHERE foodId LIKE :foodId")
    suspend fun getDataByFoodId(foodId: String): FoodEntity

    // Вставить список слов
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<FoodEntity>)
}