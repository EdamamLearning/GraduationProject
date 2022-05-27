package ru.edamamlearning.graduationproject.di.module

import dagger.Binds
import dagger.Module
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.data.repository.DomainRepositoryImpl
import ru.edamamlearning.graduationproject.data.repository.RemoteRepository
import ru.edamamlearning.graduationproject.retrofit.RemoteRepositoryImpl
import ru.edamamlearning.graduationproject.data.repository.CacheFoodRepository
import ru.edamamlearning.graduationproject.room.CacheFoodRepositoryImpl
import javax.inject.Singleton

@Module
interface DataModule {

    @Singleton
    @Binds
    fun bindDomainRepository(domainRepository: DomainRepositoryImpl): DomainRepository

    @Singleton
    @Binds
    fun bindNetworkRepository(networkRepository: RemoteRepositoryImpl): RemoteRepository

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