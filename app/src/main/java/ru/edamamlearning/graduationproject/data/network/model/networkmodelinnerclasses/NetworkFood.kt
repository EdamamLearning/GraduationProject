package ru.edamamlearning.graduationproject.data.network.model.networkmodelinnerclasses

data class NetworkFood(
    val brand: String?,
    val category: String?,
    val categoryLabel: String?,
    val foodContentsLabel: String?,
    val foodId: String?,
    val image: String?,
    val label: String?,
    val nutrients: NetworkNutrients?,
    val servingSizes: List<NetworkServingSize>?,
    val servingsPerContainer: String?,
)