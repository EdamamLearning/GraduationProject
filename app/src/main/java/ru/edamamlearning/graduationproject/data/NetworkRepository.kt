package ru.edamamlearning.graduationproject.data

import ru.edamamlearning.graduationproject.data.network.model.NetworkModel
import ru.edamamlearning.graduationproject.data.network.model.NetworkNutritionAnalysisModel

interface NetworkRepository {

    suspend fun get(text: String): NetworkModel

    suspend fun getNutritionAnalysis(text: String): NetworkNutritionAnalysisModel
}