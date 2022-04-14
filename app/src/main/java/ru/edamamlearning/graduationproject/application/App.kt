package ru.edamamlearning.graduationproject.application

import android.app.Application
import ru.edamamlearning.graduationproject.di.module.AppModule
import ru.edamamlearning.graduationproject.di.component.AppComponent
import ru.edamamlearning.graduationproject.di.component.DaggerAppComponent

class App: Application() {

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}