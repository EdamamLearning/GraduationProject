package ru.edamamlearning.graduationproject.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FoodEntity::class], version = 1, exportSchema = false
)
abstract class FoodDataBase : RoomDatabase() {

    // Возвращаем DAO
    abstract fun foodDao(): FoodDao
}