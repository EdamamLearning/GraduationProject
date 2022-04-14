package ru.edamamlearning.graduationproject.data.network.model.networkmodelinnerclasses

import com.google.gson.annotations.SerializedName

data class NetworkNutrients(
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