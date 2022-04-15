package ru.edamamlearning.graduationproject.data.network.model.nutritionanalysisinnerclasses

import com.google.gson.annotations.SerializedName

data class TotalDaily(
    @SerializedName("CA")
    val ca: CA?,
    @SerializedName("CHOCDF")
    val chocdf: CHOCDF?,
    @SerializedName("CHOLE")
    val chole: CHOLE?,
    @SerializedName("ENERCKCAL")
    val enerckcal: ENERCKCAL?,
    @SerializedName("FASAT")
    val fasat: FASAT?,
    @SerializedName("FAT")
    val fat: FAT?,
    @SerializedName("FE")
    val fe: FE?,
    @SerializedName("FOLDFE")
    val foldfe: FOLDFE?,
    @SerializedName("K")
    val k: K?,
    @SerializedName("MG")
    val mg: MG?,
    @SerializedName("NA")
    val na: NA?,
    @SerializedName("NIA")
    val nia: NIA?,
    @SerializedName("P")
    val p: P?,
    @SerializedName("PROCNT")
    val procnt: PROCNT?,
    @SerializedName("RIBF")
    val ribf: RIBF?,
    @SerializedName("THIA")
    val thia: THIA?,
    @SerializedName("VITB12")
    val vitB12: VITB12?,
    @SerializedName("VITB6A")
    val vitB6A: VITB6A?,
    @SerializedName("VITC")
    val vitC: VITC?,
    @SerializedName("VITD")
    val vitD: VITD?,
    @SerializedName("ZN")
    val nZ: ZN?,
)