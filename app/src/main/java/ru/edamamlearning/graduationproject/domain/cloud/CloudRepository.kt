package ru.edamamlearning.graduationproject.domain.cloud

import ru.edamamlearning.graduationproject.data.network.model.FoodApiDTO
import ru.edamamlearning.graduationproject.data.network.model.NetworkNutritionAnalysisModel

interface CloudRepository {

    suspend fun get(text: String): FoodApiDTO

    suspend fun getNutritionAnalysis(text: String): NetworkNutritionAnalysisModel
}