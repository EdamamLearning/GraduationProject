package ru.edamamlearning.graduationproject.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.room.entity.DiaryFoodEntity

@Dao
interface DiaryFoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(DiaryFoodDao: DiaryFoodEntity)

    @Delete
    suspend fun delete(DiaryFoodDao: DiaryFoodEntity)

    @Query("SELECT * FROM DiaryFoodEntity")
    fun getAll(): Flow<List<DiaryFoodEntity>>
}