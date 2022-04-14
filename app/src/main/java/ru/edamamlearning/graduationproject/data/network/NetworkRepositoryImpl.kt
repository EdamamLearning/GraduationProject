package ru.edamamlearning.graduationproject.data.network

import ru.edamamlearning.graduationproject.data.NetworkRepository
import ru.edamamlearning.graduationproject.data.network.model.NetworkModel
import ru.edamamlearning.graduationproject.data.network.model.NetworkNutritionAnalysisModel

class NetworkRepositoryImpl(
    private val retrofitService: RetrofitService
) : NetworkRepository {

    override suspend fun get(text: String): NetworkModel {
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