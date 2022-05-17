package ru.edamamlearning.graduationproject.domain.model

import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainHint

data class FoodDomainModel(
    val hints: List<DomainHint> = emptyList(),
)