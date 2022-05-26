package ru.edamamlearning.graduationproject.retrofit

import retrofit2.http.GET
import retrofit2.http.Query
import ru.edamamlearning.graduationproject.BuildConfig
import ru.edamamlearning.graduationproject.retrofit.model.FoodApiDTO

private const val TYPE_COOKING = "cooking"

interface RetrofitService {

    @GET("api/food-database/v2/parser")
    suspend fun getNetworkData(
        @Query("app_id") appId: String = BuildConfig.APP_ID,
        @Query("app_key") appKey: String = BuildConfig.APP_KEY,
        @Query("ingr") ingr: String,
        @Query("nutrition-type") nutritionType: String = TYPE_COOKING,
    ): FoodApiDTO
}
