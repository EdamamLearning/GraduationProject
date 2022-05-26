package ru.edamamlearning.graduationproject.data.repository

import ru.edamamlearning.graduationproject.utils.toFoodDomainModel
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.toListHistoryFoodEntity
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val localRepository: CacheFoodRepository
) : DomainRepository {

    override suspend fun getFoodModel(text: String): List<FoodDomainModel> {
        val listFood = networkRepository.get(text).toFoodDomainModel()
        localRepository.insertListFood(listFood.toListHistoryFoodEntity())
        return listFood
    }

    override suspend fun saveSearchingHistory(list: List<FoodDomainModel>) {

    }
}