package ru.edamamlearning.graduationproject.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DiaryFoodEntity(
    @PrimaryKey
    val foodId: String,
    val category: String,
    val categoryLabel: String,
    val label: String,
    val image: String,
    val brand: String,
    val foodContentsLabel: String,
    val servingsPerContainer: String,
    val carbohydrate: String,
    val energyKCal: String,
    val fat: String,
    val fiber: String,
    val protein: String,
)