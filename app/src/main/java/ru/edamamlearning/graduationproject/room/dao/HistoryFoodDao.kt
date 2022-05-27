package ru.edamamlearning.graduationproject.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity

@Dao
interface HistoryFoodDao {

    // Получить весь список продуктов
    @Query("SELECT * FROM food_table LIMIT 100")
    suspend fun getAll(): List<HistoryFoodEntity>

    // Получить конкретный продукт
    @Query("SELECT * FROM food_table WHERE foodId LIKE :foodId")
    suspend fun getDataByFoodId(foodId: String): HistoryFoodEntity

    // Вставить список слов
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistoryFoodEntity>)
}