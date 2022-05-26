package ru.edamamlearning.graduationproject.retrofit.model

import ru.edamamlearning.graduationproject.retrofit.model.nutritionanalysisinnerclasses.TotalDaily
import ru.edamamlearning.graduationproject.retrofit.model.nutritionanalysisinnerclasses.TotalNutrients
import ru.edamamlearning.graduationproject.retrofit.model.nutritionanalysisinnerclasses.TotalNutrientsKCal

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