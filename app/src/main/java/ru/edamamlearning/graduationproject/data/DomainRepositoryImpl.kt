package ru.edamamlearning.graduationproject.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.DomainModel

class DomainRepositoryImpl(
    private val repository: NetworkRepository
) : DomainRepository {

    override fun get(text: String): Flow<DomainModel> {
        return flow {
            emit(repository.get(text).toDomainModel())
        }
    }
}