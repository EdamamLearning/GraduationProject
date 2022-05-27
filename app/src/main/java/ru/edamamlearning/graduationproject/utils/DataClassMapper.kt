package ru.edamamlearning.graduationproject.utils

import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainNutrients
import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO
import ru.edamamlearning.graduationproject.retrofit.model.foodapidtoinnerclasses.NutrientsDTO
import ru.edamamlearning.graduationproject.room.entity.FavoriteFoodEntity
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity

fun FoodApiDTO.toFoodDomainModel() = this.hints.map {
    FoodDomainModel(
        category = it.food.category,
        categoryLabel = it.food.categoryLabel,
        foodId = it.food.foodId,
        label = it.food.label,
        nutrients = it.food.nutrients.toDomainNutrients(),
        image = it.food.image.orEmpty(),
        foodContentsLabel = it.food.foodContentsLabel.orEmpty(),
        brand = it.food.brand.orEmpty(),
        servingsPerContainer = it.food.servingsPerContainer.orEmpty(),
    )
}

fun NutrientsDTO.toDomainNutrients() = DomainNutrients(
    carbohydrate = this.carbohydrate.orEmpty(),
    energyKCal = this.energyKCal.orEmpty(),
    fat = this.fat.orEmpty(),
    fiber = this.fiber.orEmpty(),
    protein = this.protein.orEmpty(),
)

fun FoodDomainModel.toHistoryFoodEntity() = HistoryFoodEntity(
    foodId = this.foodId,
    category = this.category,
    categoryLabel = this.categoryLabel,
    label = this.label,
    image = this.image,
    brand = this.brand,
    foodContentsLabel = this.foodContentsLabel,
    servingsPerContainer = this.servingsPerContainer,
    carbohydrate = this.nutrients.carbohydrate,
    energyKCal = this.nutrients.energyKCal,
    fat = this.nutrients.fat,
    fiber = this.nutrients.fiber,
    protein = this.nutrients.protein,
)

fun List<FoodDomainModel>.toListHistoryFoodEntity() = this.map {
    it.toHistoryFoodEntity()
}

fun FoodDomainModel.toFavoriteFoodEntity() = FavoriteFoodEntity(
    foodId = this.foodId,
    category = this.category,
    categoryLabel = this.categoryLabel,
    label = this.label,
    image = this.image,
    brand = this.brand,
    foodContentsLabel = this.foodContentsLabel,
    servingsPerContainer = this.servingsPerContainer,
    carbohydrate = this.nutrients.carbohydrate,
    energyKCal = this.nutrients.energyKCal,
    fat = this.nutrients.fat,
    fiber = this.nutrients.fiber,
    protein = this.nutrients.protein,
)

fun List<FavoriteFoodEntity>.toListFoodDomainModel() = this.map {
    it.toFoodDomainModel()
}

fun FavoriteFoodEntity.toFoodDomainModel() = FoodDomainModel(
    foodId = this.foodId,
    category = this.category,
    categoryLabel = this.categoryLabel,
    label = this.label,
    image = this.image,
    brand = this.brand,
    foodContentsLabel = this.foodContentsLabel,
    servingsPerContainer = this.servingsPerContainer,
    nutrients = DomainNutrients(
        carbohydrate = this.carbohydrate,
        energyKCal = this.energyKCal,
        fat = this.fat,
        fiber = this.fiber,
        protein = this.protein,
    )
)