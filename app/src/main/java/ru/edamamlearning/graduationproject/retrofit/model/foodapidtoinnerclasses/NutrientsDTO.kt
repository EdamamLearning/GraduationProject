package ru.edamamlearning.graduationproject.retrofit.model.foodapidtoinnerclasses

import com.google.gson.annotations.SerializedName

data class NutrientsDTO(
    @SerializedName("CHOCDF")
    val carbohydrate: String?,
    @SerializedName("ENERC_KCAL")
    val energyKCal: String?,
    @SerializedName("FAT")
    val fat: String?,
    @SerializedName("FIBTG")
    val fiber: String?,
    @SerializedName("PROCNT")
    val protein: String?,
)