package ru.edamamlearning.graduationproject.di.module

import dagger.Binds
import dagger.Module
import ru.edamamlearning.graduationproject.data.repository.DomainRepository
import ru.edamamlearning.graduationproject.data.repository.DomainRepositoryImpl
import ru.edamamlearning.graduationproject.domain.cloud.CloudRepository
import ru.edamamlearning.graduationproject.domain.cloud.CloudRepositoryImpl
import ru.edamamlearning.graduationproject.domain.room.CacheFoodRepository
import ru.edamamlearning.graduationproject.domain.room.CacheFoodRepositoryImpl
import javax.inject.Singleton

@Module
interface DataModule {

    @Singleton
    @Binds
    fun bindDomainRepository(repository: DomainRepositoryImpl): DomainRepository

    @Singleton
    @Binds
    fun bindCloudRepository(cloudRepository: CloudRepositoryImpl): CloudRepository

    @Singleton
    @Binds
    fun bindCacheRepository(cacheFoodRepository: CacheFoodRepositoryImpl): CacheFoodRepository
//**************** Временно поставил фейк репозиторий для работы над UI, позже вернём обратно ******
    /*@Provides
    fun provideNetworkRepository(
        retrofitService: RetrofitService
    ): NetworkRepository {
        return FakeNetworkRepositoryImpl()
    }*/
}