package ru.edamamlearning.graduationproject.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

class DomainRepositoryImpl(
    private val repository: NetworkRepository
) : DomainRepository {

    override fun getFoodModel(text: String): Flow<FoodDomainModel> {
        return flow {
            emit(repository.get(text).toFoodDomainModel())
        }
    }
}