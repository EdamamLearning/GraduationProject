package ru.edamamlearning.graduationproject.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.room.entity.InfoFoodEntity

/**
 * @author Borisov Andrey on 06.06.2022
 **/
@Dao
interface InfoFoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(infoFoodDao: InfoFoodEntity)

    @Delete
    suspend fun delete(infoFoodDao: InfoFoodEntity)

    @Query("SELECT * FROM InfoFoodEntity")
    fun getAll(): Flow<List<InfoFoodEntity>>
}