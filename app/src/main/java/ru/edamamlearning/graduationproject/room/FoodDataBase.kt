package ru.edamamlearning.graduationproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.edamamlearning.graduationproject.room.dao.HistoryFoodDao
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity

@Database(
    entities = [HistoryFoodEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FoodDataBase : RoomDatabase() {

    // Возвращаем DAO
    abstract fun foodDao(): HistoryFoodDao
}