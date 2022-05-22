package ru.edamamlearning.graduationproject.di.module

import dagger.Module
import dagger.Provides
import ru.edamamlearning.graduationproject.data.NetworkRepository
import ru.edamamlearning.graduationproject.data.network.NetworkRepositoryImpl
import ru.edamamlearning.graduationproject.data.network.RetrofitService

@Module
class DataModule {

    @Provides
    fun provideNetworkRepository(
        retrofitService: RetrofitService
    ): NetworkRepository {
        return NetworkRepositoryImpl(retrofitService)
    }
//**************** Временно поставил фейк репозиторий для работы над UI, позже вернём обратно ******
    /*@Provides
    fun provideNetworkRepository(
        retrofitService: RetrofitService
    ): NetworkRepository {
        return FakeNetworkRepositoryImpl()
    }*/
}