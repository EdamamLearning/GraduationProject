package ru.edamamlearning.graduationproject.data.network.model.foodapidtoinnerclasses

data class FoodDTO(
    val category: String,
    val categoryLabel: String,
    val foodId: String,
    val label: String,
    val nutrients: NutrientsDTO,
    val image: String?,
    val foodContentsLabel: String?,
    val brand: String?,
    val servingsPerContainer: String?,
)