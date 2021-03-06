package ru.edamamlearning.graduationproject.utils.extensions

import ru.edamamlearning.graduationproject.domain.model.DiaryFoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.fooddomainmodelinnerclasses.DomainNutrients
import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO
import ru.edamamlearning.graduationproject.retrofit.model.foodapidtoinnerclasses.NutrientsDTO
import ru.edamamlearning.graduationproject.room.entity.DiaryFoodEntity
import ru.edamamlearning.graduationproject.room.entity.FavoriteFoodEntity
import ru.edamamlearning.graduationproject.room.entity.HistoryFoodEntity
import ru.edamamlearning.graduationproject.room.entity.InfoFoodEntity

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
    protein = this.nutrients.protein
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

fun List<FavoriteFoodEntity>.toListDiaryFoodDomainModel() = this.map {
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

fun HistoryFoodEntity.toFoodDomainModel() = FoodDomainModel(
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

@JvmName("toListFoodDomainModelInfoFoodEntity")
fun List<InfoFoodEntity>.toListDiaryFoodDomainModel() = this.map {
    it.toFoodDomainModel()
}

fun InfoFoodEntity.toFoodDomainModel() = FoodDomainModel(
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
        protein = this.protein
    )
)

@JvmName("toListFoodDomainModelDiaryFoodEntity")
fun List<DiaryFoodEntity>.toListDiaryFoodDomainModel() = this.map {
    it.toDiaryFoodDomainModel()
}

fun DiaryFoodEntity.toDiaryFoodDomainModel() = DiaryFoodDomainModel(
    id = this.id,
    foodId = this.foodId,
    date = this.date,
    category = this.category,
    categoryLabel = this.categoryLabel,
    label = this.label,
    image = this.image,
    brand = this.brand,
    foodContentsLabel = this.foodContentsLabel,
    servingsPerContainer = this.servingsPerContainer,
    carbohydrate = this.carbohydrate,
    energyKCal = this.energyKCal,
    fat = this.fat,
    fiber = this.fiber,
    protein = this.protein
)

fun FoodDomainModel.toInfoFoodEntity() = InfoFoodEntity(
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
    protein = this.nutrients.protein
)

fun FoodDomainModel.toDiaryFoodDomainModel(date: String) = DiaryFoodDomainModel(
    foodId = this.foodId,
    date = date,
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
    protein = this.nutrients.protein
)

fun DiaryFoodDomainModel.toDiaryFoodEntity() = DiaryFoodEntity(
    id = this.id,
    foodId = this.foodId,
    date = this.date,
    category = this.category,
    categoryLabel = this.categoryLabel,
    label = this.label,
    image = this.image,
    brand = this.brand,
    foodContentsLabel = this.foodContentsLabel,
    servingsPerContainer = this.servingsPerContainer,
    carbohydrate = this.carbohydrate,
    energyKCal = this.energyKCal,
    fat = this.fat,
    fiber = this.fiber,
    protein = this.protein
)

fun DiaryFoodDomainModel.toFoodDomainModel() = FoodDomainModel(
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
        protein = this.protein
    )
)