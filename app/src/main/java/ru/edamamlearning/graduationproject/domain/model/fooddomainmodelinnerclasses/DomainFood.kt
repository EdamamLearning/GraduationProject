package ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses

data class DomainFood(
    val category: String,
    val categoryLabel: String,
    val foodId: String,
    val label: String,
    val nutrients: DomainNutrients,
    val image: String,
    val brand: String,
    val foodContentsLabel: String,
    val servingsPerContainer: String,
)
