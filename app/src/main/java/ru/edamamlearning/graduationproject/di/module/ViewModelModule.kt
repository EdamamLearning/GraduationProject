package ru.edamamlearning.graduationproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelKey
import ru.edamamlearning.graduationproject.ui.search.SearchViewModel
import ru.edamamlearning.graduationproject.ui.startfragment.FoodFragmentViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FoodFragmentViewModel::class)
    fun bindTranslatorViewModel(vm: FoodFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun bindSearchViewModel(vm: SearchViewModel): ViewModel
}