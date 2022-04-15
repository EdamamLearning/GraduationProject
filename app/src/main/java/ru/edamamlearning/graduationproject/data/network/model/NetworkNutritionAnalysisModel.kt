package ru.edamamlearning.graduationproject.data.network.model

import ru.edamamlearning.graduationproject.data.network.model.nutritionanalysisinnerclasses.TotalDaily
import ru.edamamlearning.graduationproject.data.network.model.nutritionanalysisinnerclasses.TotalNutrients
import ru.edamamlearning.graduationproject.data.network.model.nutritionanalysisinnerclasses.TotalNutrientsKCal

data class NetworkNutritionAnalysisModel(
    val calories: String?,
    val cautions: List<String>?,
    val dietLabels: List<String>?,
    val healthLabels: List<String>?,
    val totalDaily: TotalDaily?,
    val totalNutrients: TotalNutrients?,
    val totalNutrientsKCal: TotalNutrientsKCal?,
    val totalWeight: String?,
    val uri: String?,
)