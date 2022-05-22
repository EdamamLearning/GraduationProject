package ru.edamamlearning.graduationproject.ui.utils

import kotlin.math.floor

fun roundAp(value: String): String {
    val valueDouble = value.toDouble()
    return (floor(valueDouble * 10) / 10).toString()
}