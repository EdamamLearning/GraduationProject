package ru.edamamlearning.graduationproject.data.network

import ru.edamamlearning.graduationproject.data.NetworkRepository
import ru.edamamlearning.graduationproject.data.network.model.NetworkModel

class NetworkRepositoryImpl(
    private val retrofitService: RetrofitService
) : NetworkRepository {
    override suspend fun get(text: String): NetworkModel {
        return retrofitService.getNetworkData(
            ingr = text,
            nutritionType = "cooking"
        )
    }
}