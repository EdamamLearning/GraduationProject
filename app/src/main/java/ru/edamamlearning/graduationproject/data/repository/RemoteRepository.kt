package ru.edamamlearning.graduationproject.data.repository

import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO

interface RemoteRepository {

    suspend fun get(text: String): FoodApiDTO
}