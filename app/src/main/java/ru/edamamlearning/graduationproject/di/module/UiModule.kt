package ru.edamamlearning.graduationproject.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.edamamlearning.graduationproject.ui.MainActivity
import ru.edamamlearning.graduationproject.ui.food.FoodFragment
import ru.edamamlearning.graduationproject.ui.info.InfoFragment
import ru.edamamlearning.graduationproject.ui.search.SearchFragment

@Module
interface UiModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    fun bindFoodFragment(): FoodFragment

    @ContributesAndroidInjector
    fun bindInfoFragment(): InfoFragment
}