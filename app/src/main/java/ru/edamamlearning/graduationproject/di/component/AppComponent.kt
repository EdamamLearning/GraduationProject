package ru.edamamlearning.graduationproject.di.component

import dagger.Component
import ru.edamamlearning.graduationproject.di.module.AppModule
import ru.edamamlearning.graduationproject.di.module.CoreBinder
import ru.edamamlearning.graduationproject.di.module.DataModule
import ru.edamamlearning.graduationproject.di.module.DomainModule
import ru.edamamlearning.graduationproject.di.module.RetrofitModule
import ru.edamamlearning.graduationproject.di.module.ViewModelModule
import ru.edamamlearning.graduationproject.ui.food.FoodFragment
import ru.edamamlearning.graduationproject.ui.search.SearchFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
        DomainModule::class,
        DataModule::class,
        RetrofitModule::class,
        CoreBinder::class
    ]
)
interface AppComponent {

    fun inject(foodFragment: FoodFragment)

    fun inject(searchFragment: SearchFragment)
}