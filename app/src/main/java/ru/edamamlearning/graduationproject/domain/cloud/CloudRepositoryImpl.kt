package ru.edamamlearning.graduationproject.domain.cloud

import ru.edamamlearning.graduationproject.data.network.RetrofitService
import ru.edamamlearning.graduationproject.data.network.model.FoodApiDTO
import ru.edamamlearning.graduationproject.data.network.model.NetworkNutritionAnalysisModel
import javax.inject.Inject

class CloudRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : CloudRepository {

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