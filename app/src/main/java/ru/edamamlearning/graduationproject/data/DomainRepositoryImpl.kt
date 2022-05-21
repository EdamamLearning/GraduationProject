package ru.edamamlearning.graduationproject.data

import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

class DomainRepositoryImpl(
    private val repository: NetworkRepository
) : DomainRepository {

    override suspend fun get(text: String): List<FoodDomainModel> {
        return repository.get(text).toFoodDomainModel()
    }
}