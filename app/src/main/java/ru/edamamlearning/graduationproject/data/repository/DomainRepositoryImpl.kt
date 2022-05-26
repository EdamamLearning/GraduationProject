package ru.edamamlearning.graduationproject.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.toFavoriteFoodEntity
import ru.edamamlearning.graduationproject.utils.toFoodDomainModel
import ru.edamamlearning.graduationproject.utils.toListFoodDomainModel
import ru.edamamlearning.graduationproject.utils.toListHistoryFoodEntity
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val cacheFoodRepository: CacheFoodRepository
) : DomainRepository {

    override suspend fun getFoodModel(text: String): List<FoodDomainModel> {
        val listFood = networkRepository.get(text).toFoodDomainModel()
        cacheFoodRepository.saveSearchedFood(listFood.toListHistoryFoodEntity())
        return listFood
    }

    override suspend fun saveFavoriteFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.saveFavoriteFood(foodDomainModel.toFavoriteFoodEntity())
    }

    override suspend fun deleteFavoriteFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.deleteFavoriteFood(foodDomainModel.toFavoriteFoodEntity())
    }

    override fun getAllFavoriteFoods(): Flow<List<FoodDomainModel>> {
        return cacheFoodRepository.getAllFavoriteFoods().map { it.toListFoodDomainModel() }
    }
}