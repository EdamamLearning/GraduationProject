package ru.edamamlearning.graduationproject.di.component

import dagger.Component
import ru.edamamlearning.graduationproject.di.module.AppModule
import ru.edamamlearning.graduationproject.di.module.DataModule
import ru.edamamlearning.graduationproject.di.module.DomainModule
import ru.edamamlearning.graduationproject.di.module.RetrofitModule
import ru.edamamlearning.graduationproject.di.module.ViewModelModule
import ru.edamamlearning.graduationproject.ui.search.SearchFragment
import ru.edamamlearning.graduationproject.ui.startfragment.FoodFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        DomainModule::class,
        DataModule::class,
        RetrofitModule::class,
    ]
)
interface AppComponent {

    fun inject(foodFragment: FoodFragment)

    fun inject(searchFragment: SearchFragment)
}