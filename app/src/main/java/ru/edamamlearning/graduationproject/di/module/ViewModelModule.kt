package ru.edamamlearning.graduationproject.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelKey
import ru.edamamlearning.graduationproject.ui.info.InfoViewModel
import ru.edamamlearning.graduationproject.ui.AppViewModel
import ru.edamamlearning.graduationproject.ui.diary.DiaryFragmentViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AppViewModel::class)
    fun bindAppViewModel(vm: AppViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoViewModel::class)
    fun bindInfoViewModel(vm: InfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DiaryFragmentViewModel::class)
    fun bindDiaryFragmentViewModel(vm: DiaryFragmentViewModel): ViewModel
}