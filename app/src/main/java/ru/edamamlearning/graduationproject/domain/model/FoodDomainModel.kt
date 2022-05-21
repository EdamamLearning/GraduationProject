package ru.edamamlearning.graduationproject.domain.model

import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainNutrients

data class FoodDomainModel(
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