package ru.edamamlearning.graduationproject.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * unique = true означает, что в БД не будут сохраняться повторяющиеся продукты.
 */
@Entity(
    tableName = "food_table",
    indices = [Index(value = arrayOf("foodId"), unique = true)]
)
data class HistoryFoodEntity(
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
)