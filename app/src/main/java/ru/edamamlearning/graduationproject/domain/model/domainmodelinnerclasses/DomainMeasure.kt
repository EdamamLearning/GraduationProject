package ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses

data class DomainMeasure(
    val label: String = "",
    val qualified: List<DomainQualified> = emptyList(),
    val uri: String = ""
)