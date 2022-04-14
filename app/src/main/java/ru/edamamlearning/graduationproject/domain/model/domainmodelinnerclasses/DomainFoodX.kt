package ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses

data class DomainFoodX(
    val category: String = "",
    val categoryLabel: String = "",
    val foodId: String = "",
    val image: String = "",
    val label: String = "",
    val nutrients: DomainNutrientsX = DomainNutrientsX()
)