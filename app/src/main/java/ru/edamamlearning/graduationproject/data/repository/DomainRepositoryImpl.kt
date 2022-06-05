package ru.edamamlearning.graduationproject.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.*
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val cacheFoodRepository: CacheFoodRepository
) : DomainRepository {

    override suspend fun getFoodModel(text: String): List<FoodDomainModel> {
        val listFood = remoteRepository.get(text).toFoodDomainModel()
        cacheFoodRepository.saveSearchedFood(listFood.toListHistoryFoodEntity())
        return listFood
    }

    override suspend fun getFoodModelById(foodId: String): FoodDomainModel {
        return cacheFoodRepository.getFoodModelById(foodId).toFoodDomainModel()
    }

    override suspend fun saveFavoriteFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.saveFavoriteFood(foodDomainModel.toFavoriteFoodEntity())
    }

    override suspend fun saveDiaryFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.saveDiaryFood(foodDomainModel.toDiaryFoodEntity())
    }

    override suspend fun deleteFavoriteFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.deleteFavoriteFood(foodDomainModel.toFavoriteFoodEntity())
    }

    override suspend fun deleteDiaryFood(foodDomainModel: FoodDomainModel) {
       cacheFoodRepository.deleteDiaryFood(foodDomainModel.toDiaryFoodEntity())
    }

    override suspend fun getAllHistoryFoods(): List<FoodDomainModel> {
        return cacheFoodRepository.getAllHistoryFoods().map { it.toFoodDomainModel() }
    }

    override fun getAllFavoriteFoods(): Flow<List<FoodDomainModel>> {
        return cacheFoodRepository.getAllFavoriteFoods().map { it.toListFoodDomainModel() }
    }

    override fun getAllDiaryFoods(): Flow<List<FoodDomainModel>> {
        return cacheFoodRepository.getAllDiaryFoods().map {it.toListFoodDomainModel()}
    }

}