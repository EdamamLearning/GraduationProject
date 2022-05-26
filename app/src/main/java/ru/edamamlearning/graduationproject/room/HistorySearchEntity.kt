package ru.edamamlearning.graduationproject.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity (indices = [Index(value = arrayOf("label"), unique = true)])
class HistorySearchEntity (

    @field:PrimaryKey
    @field:ColumnInfo(name = "foodId")
    var foodId: String,

    @field:ColumnInfo(name = "label")
    var label: String,
    @field:ColumnInfo(name = "category")
    var category: String,
    @field:ColumnInfo(name = "categoryLabel")
    var categoryLabel: String,
    @field:ColumnInfo(name = "nutrients")
    var nutrients: String,
    @field:ColumnInfo(name = "image")
    var image: String,
    @field:ColumnInfo(name = "foodContentsLabel")
    var foodContentsLabel: String,
    @field:ColumnInfo(name = "brand")
    var brand: String,
    @field:ColumnInfo(name = "servingsPerContainer")
    var servingsPerContainer: String,






    )
