package ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses

data class DomainHint(
    val food: DomainFood = DomainFood(),
    val measures: List<DomainMeasure> = emptyList()
)