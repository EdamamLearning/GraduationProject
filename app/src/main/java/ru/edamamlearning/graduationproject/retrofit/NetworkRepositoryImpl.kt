package ru.edamamlearning.graduationproject.retrofit

import ru.edamamlearning.graduationproject.data.repository.NetworkRepository
import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : NetworkRepository {

    override suspend fun get(text: String): FoodApiDTO {
        return retrofitService.getNetworkData(
            ingr = text
        )
    }
}