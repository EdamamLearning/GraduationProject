package ru.edamamlearning.graduationproject.application

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.edamamlearning.graduationproject.di.component.DaggerAppComponent

class App : DaggerApplication() {
    /**
     * Dagger скомпилировал нам наш компонент Dagger+ApplicationComponent,
     * который создает граф зависимостей(cicerone, router, schedulers).
     */
    override fun applicationInjector(): AndroidInjector<App> =
        DaggerAppComponent
            .builder()
            .withContext(applicationContext)
            .build()
}