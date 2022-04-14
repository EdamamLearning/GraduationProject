package ru.edamamlearning.graduationproject.data.network.model.nutritionanalysisinnerclasses

import com.google.gson.annotations.SerializedName

data class TotalNutrientsKCal(
    @SerializedName("CHOCDF_KCAL")
    val chocdfkcal: CHOCDFKCAL?,
    @SerializedName("ENERC_KCAL")
    val enerckcalxx: ENERCKCALXX?,
    @SerializedName("FAT_KCAL")
    val fatkcal: FATKCAL?,
    @SerializedName("PROCNT_KCAL")
    val procntkcal: PROCNTKCAL?,
)