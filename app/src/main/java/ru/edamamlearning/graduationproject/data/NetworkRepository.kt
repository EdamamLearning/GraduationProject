package ru.edamamlearning.graduationproject.data

import ru.edamamlearning.graduationproject.data.network.model.FoodApiDTO
import ru.edamamlearning.graduationproject.data.network.model.NetworkNutritionAnalysisModel

interface NetworkRepository {

    suspend fun get(text: String): FoodApiDTO

    suspend fun getNutritionAnalysis(text: String): NetworkNutritionAnalysisModel
}