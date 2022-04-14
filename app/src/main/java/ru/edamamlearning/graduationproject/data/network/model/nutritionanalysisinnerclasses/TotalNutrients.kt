package ru.edamamlearning.graduationproject.data.network.model.nutritionanalysisinnerclasses

import com.google.gson.annotations.SerializedName

data class TotalNutrients(
    @SerializedName("CAX")
    val cax: CAX?,
    @SerializedName("CHOCDFX")
    val chocdfx: CHOCDFX?,
    @SerializedName("CHOLEX")
    val cholex: CHOLEX?,
    @SerializedName("ENERCKCALX")
    val enerckcalx: ENERCKCALX?,
    @SerializedName("FAMS")
    val fams: FAMS?,
    @SerializedName("FAPU")
    val fapu: FAPU?,
    @SerializedName("FASATX")
    val fasatx: FASATX?,
    @SerializedName("FATX")
    val fatx: FATX?,
    @SerializedName("FEX")
    val fex: FEX?,
    @SerializedName("FOLAC")
    val folac: FOLAC?,
    @SerializedName("FOLDFEX")
    val foldfex: FOLDFEX?,
    @SerializedName("FOLFD")
    val foldd: FOLFD?,
    @SerializedName("KX")
    val kx: KX?,
    @SerializedName("MGX")
    val mg: MGX?,
    @SerializedName("NAX")
    val nax: NAX?,
    @SerializedName("NIAX")
    val niax: NIAX?,
    @SerializedName("PX")
    val px: PX?,
    @SerializedName("PROCNTX")
    val procntx: PROCNTX?,
    @SerializedName("RIBFX")
    val ribfx: RIBFX?,
    @SerializedName("THIAX")
    val thiax: THIAX?,
    @SerializedName("VITB12X")
    val vitB12x: VITB12X?,
    @SerializedName("VITB6AX")
    val vitB6Ax: VITB6AX?,
    @SerializedName("VITCX")
    val vitCx: VITCX?,
    @SerializedName("VITDX")
    val vitDx: VITDX?,
    @SerializedName("WATER")
    val water: WATER?,
    @SerializedName("ZNX")
    val zn: ZNX?,
)