package ru.edamamlearning.graduationproject.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.edamamlearning.graduationproject.room.dao.DiaryFoodDao
import ru.edamamlearning.graduationproject.room.dao.FavoriteFoodDao
import ru.edamamlearning.graduationproject.room.dao.HistoryFoodDao
import ru.edamamlearning.graduationproject.room.dao.InfoFoodDAO
import ru.edamamlearning.graduationproject.room.entity.DiaryFoodEntity
import ru.edamamlearning.graduationproject.room.entity.FavoriteFoodEntity
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity
import ru.edamamlearning.graduationproject.room.entity.InfoFoodEntity

@Database(
    entities = [HistoryFoodEntity::class, FavoriteFoodEntity::class, DiaryFoodEntity::class, InfoFoodEntity::class],
    version = 4,
    exportSchema = false
)
abstract class FoodDataBase : RoomDatabase() {

    // Возвращаем DAO
    abstract fun historyFoodDao(): HistoryFoodDao
    abstract fun favoriteFoodDao(): FavoriteFoodDao
    abstract fun infoFoodDao(): InfoFoodDAO
    abstract fun diaryFoodDao(): DiaryFoodDao
}