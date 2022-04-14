package ru.edamamlearning.graduationproject.domain

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.domain.model.DomainModel

interface DomainRepository {

    fun get(text: String): Flow<DomainModel>
}