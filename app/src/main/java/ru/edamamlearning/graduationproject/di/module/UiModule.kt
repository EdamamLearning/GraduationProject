package ru.edamamlearning.graduationproject.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.edamamlearning.graduationproject.ui.MainActivity
import ru.edamamlearning.graduationproject.ui.diary.DiaryFragment
import ru.edamamlearning.graduationproject.ui.favorite.FavoriteFragment
import ru.edamamlearning.graduationproject.ui.info.InfoFragment
import ru.edamamlearning.graduationproject.ui.search.SearchFragment

@Module
interface UiModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    fun bindInfoFragment(): InfoFragment

    @ContributesAndroidInjector
    fun bindFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector
    fun bindDiaryFragment(): DiaryFragment
}