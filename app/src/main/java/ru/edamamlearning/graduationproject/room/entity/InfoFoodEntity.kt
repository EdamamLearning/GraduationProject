package ru.edamamlearning.graduationproject.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Borisov Andrey on 06.06.2022
 **/
@Entity
data class InfoFoodEntity(
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
