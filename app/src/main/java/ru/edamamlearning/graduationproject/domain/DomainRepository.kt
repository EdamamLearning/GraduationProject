package ru.edamamlearning.graduationproject.domain

import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

interface DomainRepository {

    fun getFoodModel(text: String): Flow<FoodDomainModel>
}