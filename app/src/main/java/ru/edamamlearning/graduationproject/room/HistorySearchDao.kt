package ru.edamamlearning.graduationproject.room

import androidx.room.*

@Dao
interface HistorySearchDao {
    @Query("SELECT * FROM HistorySearchEntity")
    suspend fun all(): List<HistorySearchEntity>

    @Query("SELECT * FROM HistorySearchEntity WHERE label LIKE :label")
    suspend fun getDataByLabel(label: String): HistorySearchEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistorySearchEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistorySearchEntity>)

    @Update
    suspend fun update(entity: HistorySearchEntity)

    @Delete
    suspend fun delete(entity: HistorySearchEntity)
}
