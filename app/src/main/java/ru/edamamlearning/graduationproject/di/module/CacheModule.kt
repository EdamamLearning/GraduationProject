package ru.edamamlearning.graduationproject.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.edamamlearning.graduationproject.room.FoodDataBase
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): FoodDataBase =
        Room.databaseBuilder(
            context,
            FoodDataBase::class.java,
            "food.db"
        )
            /**fallbackToDestructiveMigration - уничтожить и восстановить базу данных,
             * что означает потерю данных.*/
            .fallbackToDestructiveMigration()
            .build()
}