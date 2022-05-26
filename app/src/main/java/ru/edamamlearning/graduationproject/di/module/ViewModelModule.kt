package ru.edamamlearning.graduationproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelKey
import ru.edamamlearning.graduationproject.ui.favorite.FavoriteViewModel
import ru.edamamlearning.graduationproject.ui.food.FoodViewModel
import ru.edamamlearning.graduationproject.ui.search.SearchViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FoodViewModel::class)
    fun bindFoodViewModel(vm: FoodViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(vm: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun bindFavoriteViewModel(vm: FavoriteViewModel): ViewModel
}