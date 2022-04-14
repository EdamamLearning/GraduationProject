package ru.edamamlearning.graduationproject.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.edamamlearning.graduationproject.BuildConfig
import ru.edamamlearning.graduationproject.data.network.model.NetworkModel

interface RetrofitService {

    @GET("api/food-database/v2/parser")
    suspend fun getNetworkData(
        @Query("app_id") appId: String = BuildConfig.APP_ID,
        @Query("app_key") appKey: String = BuildConfig.APP_KEY,
        @Query("ingr") ingr: String,
        @Query("nutrition-type") nutritionType: String,
    ): NetworkModel


}
