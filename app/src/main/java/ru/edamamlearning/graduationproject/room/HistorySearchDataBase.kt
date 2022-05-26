package ru.edamamlearning.graduationproject.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistorySearchEntity:: class), version = 1, exportSchema = false)
abstract class HistorySearchDataBase: RoomDatabase() {
    abstract fun historyDao(): HistorySearchDao
}

