package ru.edamamlearning.graduationproject.data.network

import ru.edamamlearning.graduationproject.data.network.model.FoodApiDTO
import ru.edamamlearning.graduationproject.data.network.model.NetworkNutritionAnalysisModel
import ru.edamamlearning.graduationproject.data.network.model.foodapidtoinnerclasses.FoodDTO
import ru.edamamlearning.graduationproject.data.network.model.foodapidtoinnerclasses.HintDTO
import ru.edamamlearning.graduationproject.data.network.model.foodapidtoinnerclasses.NutrientsDTO
import ru.edamamlearning.graduationproject.domain.cloud.CloudRepository

class FakeNetworkRepositoryImpl : CloudRepository {

    override suspend fun get(text: String): FoodApiDTO {
        return fakeNetworkModel
    }

    override suspend fun getNutritionAnalysis(text: String): NetworkNutritionAnalysisModel {
        return fakeNetworkNutritionAnalysisModel
    }

    private val fakeNetworkModel: FoodApiDTO =
        FoodApiDTO(
            listOf(
                HintDTO(
                    FoodDTO(
                        category = "Packaged foods",
                        categoryLabel = "food",
                        foodId = "food_budu8jqaufljzmbq0cuyzadpy6aw",
                        label = "Egg Beaters  Egg Product  Egg Whites",
                        nutrients = NutrientsDTO("1.33", "406", "33.82", "0", "24.04"),
                        image = "https://www.edamam.com/food-img/e52/e522611330ccd8828976241b71425aca.jpg",
                        foodContentsLabel = "Egg Whites",
                        brand = "Egg Beaters",
                        servingsPerContainer = "2",
                    )
                ),
                HintDTO(
                    FoodDTO(
                        category = "Generic foods",
                        categoryLabel = "food",
                        foodId = "food_budu8jqaufljzmbq0cuyzadpy6aw",
                        label = "Hard-Boiled Eggs",
                        nutrients = NutrientsDTO("1.33", "406", "33.82", "0", "24.04"),
                        image = "https://www.edamam.com/food-img/e54/e54c012fabed0f9cf211a817d1e23c5c.jpg",
                        foodContentsLabel = "",
                        brand = "",
                        servingsPerContainer = "",
                    ),
                ),
                HintDTO(
                    FoodDTO(
                        category = "Generic foods",
                        categoryLabel = "food",
                        foodId = "food_budu8jqaufljzmbq0cuyzadpy6aw",
                        label = "Fried Egg",
                        nutrients = NutrientsDTO("1.33", "406", "33.82", "0", "24.04"),
                        image = "https://www.edamam.com/food-img/f8b/f8b60f2c6e9b015c5a5e692be56784dc.jpg",
                        foodContentsLabel = "Egg Whites",
                        brand = "Egg Beaters",
                        servingsPerContainer = "2",
                    ),
                ),
                HintDTO(
                    FoodDTO(
                        category = "Packaged foods",
                        categoryLabel = "food",
                        foodId = "food_ao5zke5aq85e1oavdi4kla1cms6g",
                        label = "Eggs",
                        nutrients = NutrientsDTO("1.33", "406", "33.82", "0", "24.04"),
                        image = "https://www.edamam.com/food-img/7bc/7bc934323e4b276d9a699b05947aebf1.png",
                        foodContentsLabel = "Egg Whites",
                        brand = "Pete And Gerry's Organics, Llc.",
                        servingsPerContainer = "1",
                    ),
                )
            )
        )

    private val fakeNetworkNutritionAnalysisModel: NetworkNutritionAnalysisModel =
        NetworkNutritionAnalysisModel(
            null, null, null, null, null,
            null, null, null, null
        )
}