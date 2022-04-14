package ru.edamamlearning.graduationproject.data

import ru.edamamlearning.graduationproject.data.network.model.NetworkModel
import ru.edamamlearning.graduationproject.data.network.model.networkmodelinnerclasses.*
import ru.edamamlearning.graduationproject.domain.model.DomainModel
import ru.edamamlearning.graduationproject.domain.model.domainmodelinnerclasses.*

fun NetworkModel.toDomainModel() = DomainModel(
    text = this.text.toString(),
    parsed = this.parsed?.toListDomainParsed() ?: emptyList(),
    links = this.links?.toDomainLinks() ?: DomainLinks(),
    hints = this.hints?.toListDomainHint() ?: emptyList()
)

//List<NetworkHint>.toListDomainHint()
fun List<NetworkHint>.toListDomainHint() = this.map {
    it.toDomainHint()
}

fun NetworkHint.toDomainHint() = DomainHint(
    measures = this.measures?.toListDomainMeasure() ?: emptyList(),
    food = this.food?.toDomainFood() ?: DomainFood()
)

fun NetworkFood.toDomainFood() = DomainFood(
    brand = this.brand.toString(),
    category = this.category.toString(),
    categoryLabel = this.categoryLabel.toString(),
    foodContentsLabel = this.foodContentsLabel.toString(),
    foodId = this.foodId.toString(),
    image = this.image.toString(),
    label = this.label.toString(),
    nutrients = this.nutrients?.toDomainNutrients() ?: DomainNutrients(),
    servingSizes = this.servingSizes?.toListDomainServingSize() ?: emptyList(),
    servingsPerContainer = this.servingsPerContainer.toString(),
)

fun NetworkNutrients.toDomainNutrients() = DomainNutrients(
    carbohydrate = this.carbohydrate.toString(),
    energyKCal = this.energyKCal.toString(),
    fat = this.fat.toString(),
    fiber = this.fiber.toString(),
    protein = this.protein.toString(),
)

fun List<NetworkServingSize>.toListDomainServingSize() = this.map {
    it.toDomainServingSize()
}

fun NetworkServingSize.toDomainServingSize() = DomainServingSize(
    label = this.label.toString(),
    quantity = this.quantity.toString(),
    uri = this.uri.toString(),
)

fun List<NetworkMeasure>.toListDomainMeasure() = this.map {
    it.toDomainMeasure()
}

fun NetworkMeasure.toDomainMeasure() = DomainMeasure(
    label = this.label.toString(),
    uri = this.uri.toString(),
    qualified = this.qualified?.toListDomainQualified() ?: emptyList()
)

fun List<NetworkQualified>.toListDomainQualified() = this.map {
    it.toListDomainQualified()
}

fun NetworkQualified.toListDomainQualified() = DomainQualified(
    qualifiers = this.qualifiers?.toListDomainQualifier() ?: emptyList()
)

fun List<NetworkQualifier>.toListDomainQualifier() = this.map {
    it.toDomainQualifier()
}

fun NetworkQualifier.toDomainQualifier() = DomainQualifier(
    label = this.label.toString(),
    uri = this.uri.toString(),
)

//NetworkLinks.toDomainLinks()
fun NetworkLinks.toDomainLinks() = DomainLinks(
    next = this.next?.toDomainNext() ?: DomainNext()
)

fun NetworkNext.toDomainNext() = DomainNext(
    href = this.href.toString(),
    title = this.title.toString(),
)

// List<NetworkParsed>.toListDomainParsed()
fun List<NetworkParsed>.toListDomainParsed() = this.map {
    it.toDomainParsed()
}

fun NetworkParsed.toDomainParsed() = DomainParsed(
    food = this.food?.toDomainFoodX() ?: DomainFoodX()
)

fun NetworkFoodX.toDomainFoodX() = DomainFoodX(
    category = this.category.toString(),
    categoryLabel = this.categoryLabel.toString(),
    foodId = this.foodId.toString(),
    image = this.image.toString(),
    label = this.label.toString(),
    nutrients = this.nutrients?.toDomainNutrientsX() ?: DomainNutrientsX(),
)

fun NetworkNutrientsX.toDomainNutrientsX() = DomainNutrientsX(
    carbohydrate = this.carbohydrate.toString(),
    energyKCal = this.energyKCal.toString(),
    fat = this.fat.toString(),
    fiber = this.fiber.toString(),
    protein = this.protein.toString(),
)