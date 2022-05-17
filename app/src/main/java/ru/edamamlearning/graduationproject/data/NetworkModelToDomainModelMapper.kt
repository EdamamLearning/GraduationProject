package ru.edamamlearning.graduationproject.data

import ru.edamamlearning.graduationproject.data.network.model.FoodApiDTO
import ru.edamamlearning.graduationproject.data.network.model.foodapidtoinnerclasses.FoodDTO
import ru.edamamlearning.graduationproject.data.network.model.foodapidtoinnerclasses.HintDTO
import ru.edamamlearning.graduationproject.data.network.model.foodapidtoinnerclasses.NutrientsDTO
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainFood
import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainHint
import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainNutrients

fun FoodApiDTO.toFoodDomainModel() = FoodDomainModel(
    hints = this.hints.toDomainHints()
)

fun List<HintDTO>.toDomainHints() = this.map {
    DomainHint(
        food = it.food.toDomainFood()
    )
}

fun FoodDTO.toDomainFood() = DomainFood(
    category = this.category,
    categoryLabel = this.categoryLabel,
    foodId = this.foodId,
    label = this.label,
    nutrients = this.nutrients.toDomainNutrients(),
    image = this.image.orEmpty(),
    foodContentsLabel = this.foodContentsLabel.orEmpty(),
    brand = this.brand.orEmpty(),
    servingsPerContainer = this.servingsPerContainer.orEmpty(),
)

fun NutrientsDTO.toDomainNutrients() = DomainNutrients(
    carbohydrate = this.carbohydrate.orEmpty(),
    energyKCal = this.energyKCal.orEmpty(),
    fat = this.fat.orEmpty(),
    fiber = this.fiber.orEmpty(),
    protein = this.protein.orEmpty(),
)