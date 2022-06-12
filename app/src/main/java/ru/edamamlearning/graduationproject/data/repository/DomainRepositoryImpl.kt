package ru.edamamlearning.graduationproject.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.DiaryFoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.extensions.*
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val cacheFoodRepository: CacheFoodRepository
) : DomainRepository {

    override suspend fun getFoodModel(text: String): List<FoodDomainModel> {
        val listFood: List<FoodDomainModel> = remoteRepository.get(text).toFoodDomainModel()
        listFood.distinctBy { it.foodId }
            .distinctBy { it.label }
        cacheFoodRepository.saveSearchedFood(listFood.toListHistoryFoodEntity())
        return listFood
    }

    override suspend fun getFoodModelById(foodId: String): FoodDomainModel {
        return cacheFoodRepository.getFoodModelById(foodId).toFoodDomainModel()
    }

    override suspend fun saveInfoFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.saveInfoFood(foodDomainModel.toInfoFoodEntity())
    }

    override suspend fun deleteInfoFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.deleteInfoFood(foodDomainModel.toInfoFoodEntity())
    }

    override fun getAllInfoFoods(): Flow<List<FoodDomainModel>> {
        return cacheFoodRepository.getAllInfoFood().map { it.toListDiaryFoodDomainModel() }
    }

    override suspend fun saveFavoriteFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.saveFavoriteFood(foodDomainModel.toFavoriteFoodEntity())
    }

    override suspend fun saveDiaryFood(diaryFoodDomainModel: DiaryFoodDomainModel) {
        cacheFoodRepository.saveDiaryFood(diaryFoodDomainModel.toDiaryFoodEntity())
    }

    override suspend fun deleteFavoriteFood(foodDomainModel: FoodDomainModel) {
        cacheFoodRepository.deleteFavoriteFood(foodDomainModel.toFavoriteFoodEntity())
    }

    override suspend fun deleteDiaryFood(diaryFoodDomainModel: DiaryFoodDomainModel) {
        cacheFoodRepository.deleteDiaryFood(diaryFoodDomainModel.toDiaryFoodEntity())
    }

    override suspend fun getAllHistoryFoods(): List<FoodDomainModel> {
        return cacheFoodRepository.getAllHistoryFoods().map { it.toFoodDomainModel() }
    }

    override fun getAllFavoriteFoods(): Flow<List<FoodDomainModel>> {
        return cacheFoodRepository.getAllFavoriteFoods().map { it.toListDiaryFoodDomainModel() }
    }

    override fun getAllDiaryFoods(): Flow<List<DiaryFoodDomainModel>> {
        return cacheFoodRepository.getAllDiaryFoods().map { it.toListDiaryFoodDomainModel() }
    }

    override suspend fun getDiaryFoodsByDate(date: String): List<DiaryFoodDomainModel> {
        return cacheFoodRepository.getDiaryFoodsByDate(date).toListDiaryFoodDomainModel()
    }
}