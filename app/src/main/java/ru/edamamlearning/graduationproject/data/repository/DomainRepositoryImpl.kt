package ru.edamamlearning.graduationproject.data.repository

import ru.edamamlearning.graduationproject.data.toFoodDomainModel
import ru.edamamlearning.graduationproject.domain.cloud.CloudRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.domain.room.CacheFoodRepository
import ru.edamamlearning.graduationproject.domain.room.FoodEntity
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val repository: CloudRepository,
    private val localRepository: CacheFoodRepository
) : DomainRepository {

    override suspend fun getFoodModel(text: String): List<FoodDomainModel> {
        val listFood = repository.get(text).toFoodDomainModel()
        localRepository.insertListFood(listFood)
        return listFood
    }

    override suspend fun getSavedFood(): List<FoodEntity> {
        return localRepository.getSavedFood()
    }
}