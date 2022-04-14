package ru.edamamlearning.graduationproject.di.module

import dagger.Module
import dagger.Provides
import ru.edamamlearning.graduationproject.data.DomainRepositoryImpl
import ru.edamamlearning.graduationproject.data.NetworkRepository
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.GetUseCase

@Module
class DomainModule {

    @Provides
    fun provideGetUseCase(
        domainRepository: DomainRepository
    ): GetUseCase {
        return GetUseCase(domainRepository)
    }

    @Provides
    fun provideDomainRepository(
        networkRepository: NetworkRepository
    ): DomainRepository {
        return DomainRepositoryImpl(networkRepository)
    }
}