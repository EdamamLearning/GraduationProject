package ru.edamamlearning.graduationproject.retrofit

import ru.edamamlearning.graduationproject.data.repository.RemoteRepository
import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : RemoteRepository {

    override suspend fun get(text: String): FoodApiDTO {
        return retrofitService.getNetworkData(
            ingr = text
        )
    }
}