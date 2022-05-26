package ru.edamamlearning.graduationproject.data.repository

import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO
import ru.edamamlearning.graduationproject.retrofit.model.NetworkNutritionAnalysisModel

interface NetworkRepository {

    suspend fun get(text: String): FoodApiDTO

    suspend fun getNutritionAnalysis(text: String): NetworkNutritionAnalysisModel
}