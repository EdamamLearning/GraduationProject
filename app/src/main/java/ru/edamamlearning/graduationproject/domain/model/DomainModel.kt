package ru.edamamlearning.graduationproject.domain.model

import ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses.DomainHint
import ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses.DomainLinks
import ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses.DomainParsed

data class DomainModel(
    val links: DomainLinks = DomainLinks(),
    val hints: List<DomainHint> = emptyList(),
    val parsed: List<DomainParsed> = emptyList(),
    val text: String = ""
)