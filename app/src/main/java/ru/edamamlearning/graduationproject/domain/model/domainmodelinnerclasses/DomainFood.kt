package ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses

data class DomainFood(
    val brand: String = "",
    val category: String = "",
    val categoryLabel: String = "",
    val foodContentsLabel: String = "",
    val foodId: String = "",
    val image: String = "",
    val label: String = "",
    val nutrients: DomainNutrients = DomainNutrients(),
    val servingSizes: List<DomainServingSize> = emptyList(),
    val servingsPerContainer: String = "",
)