package ru.edamamlearning.graduationproject.di.component

import dagger.Component
import ru.edamamlearning.graduationproject.di.module.*
import ru.edamamlearning.graduationproject.ui.startfragment.StartFragment
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

    fun inject(startFragment: StartFragment)
}