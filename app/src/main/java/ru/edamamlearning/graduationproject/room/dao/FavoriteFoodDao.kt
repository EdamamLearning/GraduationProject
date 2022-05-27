package ru.edamamlearning.graduationproject.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.room.entity.FavoriteFoodEntity

@Dao
interface FavoriteFoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteFoodDao: FavoriteFoodEntity)

    @Delete
    suspend fun delete(favoriteFoodDao: FavoriteFoodEntity)

    @Query("SELECT * FROM FavoriteFoodEntity")
    fun getAll(): Flow<List<FavoriteFoodEntity>>
}