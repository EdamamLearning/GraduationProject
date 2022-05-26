package ru.edamamlearning.graduationproject.retrofit

import ru.edamamlearning.graduationproject.data.repository.NetworkRepository
import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO
import ru.edamamlearning.graduationproject.retrofit.model.NetworkNutritionAnalysisModel
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : NetworkRepository {

    override suspend fun get(text: String): FoodApiDTO {
        return retrofitService.getNetworkData(
            ingr = text,
            nutritionType = "cooking"
        )
    }

    override suspend fun getNutritionAnalysis(text: String): NetworkNutritionAnalysisModel {
        return retrofitService.getNutritionAnalysis(
            nutrition_type = "cooking",
            ingr = "100%20g%20apple",
        )
    }
}