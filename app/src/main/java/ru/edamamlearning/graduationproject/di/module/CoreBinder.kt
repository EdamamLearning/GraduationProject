package ru.edamamlearning.graduationproject.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.edamamlearning.graduationproject.core.CoroutineContextProvider
import ru.edamamlearning.graduationproject.core.CoroutineContextProviderImpl
import ru.edamamlearning.graduationproject.core.NetworkObserver
import ru.edamamlearning.graduationproject.core.resourcesprovider.ResourceProviderImpl
import ru.edamamlearning.graduationproject.core.resourcesprovider.ResourcesProvider
import ru.edamamlearning.graduationproject.utils.message.SystemMessageNotifier
import javax.inject.Singleton

@Module
interface CoreBinder {

    @Binds
    @Singleton
    fun provideCoroutineContextProvider(
        impl: CoroutineContextProviderImpl
    ): CoroutineContextProvider

    @Binds
    @Singleton
    fun provideResourcesProvider(impl: ResourceProviderImpl): ResourcesProvider

    companion object {

        @Provides
        @Singleton
        fun provideNetworkObserver(
            context: Context
        ): NetworkObserver {
            return NetworkObserver(context)
        }

        @Provides
        @Singleton
        fun provideSystemMessageNotifier(): SystemMessageNotifier {
            return SystemMessageNotifier()
        }
    }
}