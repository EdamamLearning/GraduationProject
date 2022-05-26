package ru.edamamlearning.graduationproject.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.edamamlearning.graduationproject.application.App
import ru.edamamlearning.graduationproject.di.module.CacheModule
import ru.edamamlearning.graduationproject.di.module.CoreBinder
import ru.edamamlearning.graduationproject.di.module.DataModule
import ru.edamamlearning.graduationproject.di.module.RetrofitModule
import ru.edamamlearning.graduationproject.di.module.UiModule
import ru.edamamlearning.graduationproject.di.module.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        DataModule::class,
        RetrofitModule::class,
        CoreBinder::class,
        UiModule::class,
        CacheModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        fun build(): AppComponent
    }
}