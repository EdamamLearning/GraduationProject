package ru.edamamlearning.graduationproject.domain.model

data class DiaryFoodDomainModel(
    val foodId: String,
    val date: String,
    val category: String,
    val categoryLabel: String,
    val label: String,
    val image: String,
    val brand: String,
    val foodContentsLabel: String,
    val servingsPerContainer: String,
    val carbohydrate: String,
    val energyKCal: String,
    val fat: String,
    val fiber: String,
    val protein: String,
)
