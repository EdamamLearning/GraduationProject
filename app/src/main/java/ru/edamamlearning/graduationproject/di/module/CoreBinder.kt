package ru.edamamlearning.graduationproject.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.edamamlearning.graduationproject.core.CoroutineContextProvider
import ru.edamamlearning.graduationproject.core.CoroutineContextProviderImpl
import ru.edamamlearning.graduationproject.core.NetworkObserver
import javax.inject.Singleton

@Module
interface CoreBinder {

    @Binds
    @Singleton
    fun provideCoroutineContextProvider(
        impl: CoroutineContextProviderImpl
    ): CoroutineContextProvider

    companion object {
        @Provides
        @Singleton
        fun provideNetworkObserver(
            context: Context
        ): NetworkObserver {
            return NetworkObserver(context)
        }
    }
}