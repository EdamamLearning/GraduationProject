package ru.edamamlearning.graduationproject.data.network.fakerepository

import ru.edamamlearning.graduationproject.data.NetworkRepository
import ru.edamamlearning.graduationproject.data.network.model.NetworkModel
import ru.edamamlearning.graduationproject.data.network.model.NetworkNutritionAnalysisModel
import ru.edamamlearning.graduationproject.data.network.model.networkmodelinnerclasses.*

class FakeNetworkRepositoryImpl : NetworkRepository {

    override suspend fun get(text: String): NetworkModel {
        return fakeNetworkModel
    }

    override suspend fun getNutritionAnalysis(text: String): NetworkNutritionAnalysisModel {
        return fakeNetworkNutritionAnalysisModel
    }

    private val fakeNetworkModel: NetworkModel =
        NetworkModel(
            NetworkLinks(NetworkNext("href", "NetworkNextTitle")),
            listOf(
                NetworkHint(
                    NetworkFood(
                        "Garant",
                        "Packaged foods",
                        "food",
                        "Eggs",
                        "food_bzfr5k1btcij4nap2taj6arp5x48",
                        "https://www.edamam.com/food-img/065/065130e51699c2340f6f2e5df7e25911.jpg",
                        "Garant 10 Extra Stora Agg Fran Frigaende Hons Inomhus 10pk",
                        NetworkNutrients("0", "140", "10", "0", "12"),
                        listOf(
                            NetworkServingSize(
                                "Gram",
                                "100",
                                "http://www.edamam.com/ontologies/edamam.owl#Measure_gram"
                            ),
                            NetworkServingSize(
                                "Egg",
                                "10",
                                "http://www.edamam.com/ontologies/edamam.owl#Measure_egg"
                            )
                        ),
                        "1"
                    ), listOf(
                        NetworkMeasure(
                            "Serving",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_serving"
                        ),
                        NetworkMeasure(
                            "Egg",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_egg"
                        ),
                        NetworkMeasure(
                            "Package",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_package"
                        ),
                        NetworkMeasure(
                            "Gram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_gram"
                        ),
                        NetworkMeasure(
                            "Ounce",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce"
                        ),
                        NetworkMeasure(
                            "Pound",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_pound"
                        ),
                        NetworkMeasure(
                            "Kilogram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram"
                        )
                    )
                ),
                NetworkHint(
                    NetworkFood(
                        "Garant",
                        "Generic foods",
                        "food",
                        "Eggs",
                        "food_bhppgmha1u27voagb8eptbp9g376",
                        "https://www.edamam.com/food-img/bcd/bcd94dde1fcde1475b5bf0540f821c5d.jpg",
                        "Cheese",
                        NetworkNutrients("1.33", "406", "33.82", "0", "24.04"),
                        null,
                        null
                    ), listOf(
                        NetworkMeasure(
                            "Whole",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_unit"
                        ),
                        NetworkMeasure(
                            "Block",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_block"
                        ),
                        NetworkMeasure(
                            "Piece",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_piece"
                        ),
                        NetworkMeasure(
                            "Serving",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_serving"
                        ),
                        NetworkMeasure(
                            "Slice",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_slice"
                        ),
                        NetworkMeasure(
                            "Chip",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_chip"
                        ),
                        NetworkMeasure(
                            "Package",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_package"
                        ),
                        NetworkMeasure(
                            "Gram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_gram"
                        ),
                        NetworkMeasure(
                            "Ounce",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce"
                        ),
                        NetworkMeasure(
                            "Pound",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_pound"
                        ),
                        NetworkMeasure(
                            "Kilogram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram"
                        ),
                        NetworkMeasure(
                            "Cup",
                            listOf(NetworkQualified(listOf(
                                NetworkQualifier("diced", "http://www.edamam.com/ontologies/edamam.owl#Qualifier_diced"),
                                NetworkQualifier("shredded", "http://www.edamam.com/ontologies/edamam.owl#Qualifier_shredded"),
                                NetworkQualifier("melted", "http://www.edamam.com/ontologies/edamam.owl#Qualifier_melted")
                            ))),
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_cup"
                        ),
                        NetworkMeasure(
                            "Cubic inch",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_cubic_inch"
                        )
                    )
                ),
                NetworkHint(
                    NetworkFood(
                        null,
                        "Generic foods",
                        "food",
                        "Eggs",
                        "food_bq9prtpbov0mzca37nef3am152os",
                        "https://www.edamam.com/food-img/702/7023ac63ef897bab1f6ea399316748d7.png",
                        "Brick Cheese",
                        NetworkNutrients("2.79", "371", "29.68", "0", "23.24"),
                        null,
                        null
                    ), listOf(
                        NetworkMeasure(
                            "Serving",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_serving"
                        ),
                        NetworkMeasure(
                            "Slice",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_slice"
                        ),
                        NetworkMeasure(
                            "Gram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_gram"
                        ),
                        NetworkMeasure(
                            "Ounce",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce"
                        ),
                        NetworkMeasure(
                            "Pound",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_pound"
                        ),
                        NetworkMeasure(
                            "Kilogram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram"
                        ),
                        NetworkMeasure(
                            "Cup",
                            listOf(NetworkQualified(listOf(
                                NetworkQualifier("diced", "http://www.edamam.com/ontologies/edamam.owl#Qualifier_diced"),
                                NetworkQualifier("shredded", "http://www.edamam.com/ontologies/edamam.owl#Qualifier_shredded")
                            ))),
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_cup"
                        ),
                        NetworkMeasure(
                            "Cubic inch",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_cubic_inch"
                        )
                    )
                ),
                NetworkHint(
                    NetworkFood(
                        null,
                        "Generic foods",
                        "food",
                        "Eggs",
                        "food_bnuu4nzb68xes2a18874ebnzdata",
                        "https://www.edamam.com/food-img/9e4/9e4ffc57473590221cb97ccf5354e42b.jpg",
                        "Cheese Cheshire",
                        NetworkNutrients("4.78", "387", "30.6", "0", "23.37"),
                        null,
                        null
                    ), listOf(
                        NetworkMeasure(
                            "Serving",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_serving"
                        ),
                        NetworkMeasure(
                            "Gram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_gram"
                        ),
                        NetworkMeasure(
                            "Ounce",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_ounce"
                        ),
                        NetworkMeasure(
                            "Pound",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_pound"
                        ),
                        NetworkMeasure(
                            "Kilogram",
                            null,
                            "http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram"
                        )
                    )
                )
            ),
            listOf(
                NetworkParsed(
                    NetworkFoodX("Generic foods","food","food_bhppgmha1u27voagb8eptbp9g376",
                        "https://www.edamam.com/food-img/bcd/bcd94dde1fcde1475b5bf0540f821c5d.jpg","Cheese",
                        NetworkNutrientsX("1.33","406","33.82","0","24.04"))
                )
            ),
            "cheese"
        )


    private val fakeNetworkNutritionAnalysisModel: NetworkNutritionAnalysisModel =
        NetworkNutritionAnalysisModel(
            null, null, null, null, null,
            null, null, null, null
        )
}