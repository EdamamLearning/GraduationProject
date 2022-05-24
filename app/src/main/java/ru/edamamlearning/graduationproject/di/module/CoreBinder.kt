package ru.edamamlearning.graduationproject.di.module

import dagger.Binds
import dagger.Module
import ru.edamamlearning.graduationproject.core.CoroutineContextProvider
import ru.edamamlearning.graduationproject.core.CoroutineContextProviderImpl
import javax.inject.Singleton

@Module
interface CoreBinder {

    @Binds
    @Singleton
    fun provideCoroutineContextProvider(
        impl: CoroutineContextProviderImpl,
    ): CoroutineContextProvider
}