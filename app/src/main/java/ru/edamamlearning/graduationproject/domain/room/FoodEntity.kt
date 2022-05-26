package ru.edamamlearning.graduationproject.domain.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel

/**
 * unique = true означает, что в БД не будут сохраняться повторяющиеся продукты.
 */
@Entity(
    tableName = "food_table",
    indices = [Index(value = arrayOf("foodId"), unique = true)]
)
data class FoodEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "foodId")
    val foodId: String,
    @field:ColumnInfo(name = "category")
    val category: String,
    @field:ColumnInfo(name = "categoryLabel")
    val categoryLabel: String,
    @field:ColumnInfo(name = "label")
    val label: String,
    @field:ColumnInfo(name = "image")
    val image: String,
    @field:ColumnInfo(name = "brand")
    val brand: String,
    @field:ColumnInfo(name = "foodContentsLabel")
    val foodContentsLabel: String,
    @field:ColumnInfo(name = "servingsPerContainer")
    val servingsPerContainer: String
) {

    object Mapper {

        fun map(foodModel: FoodDomainModel): FoodEntity =
            FoodEntity(
                foodId = foodModel.foodId,
                category = foodModel.category,
                categoryLabel = foodModel.categoryLabel,
                label = foodModel.label,
                image = foodModel.image,
                brand = foodModel.brand,
                foodContentsLabel = foodModel.foodContentsLabel,
                servingsPerContainer = foodModel.servingsPerContainer
            )
    }
}