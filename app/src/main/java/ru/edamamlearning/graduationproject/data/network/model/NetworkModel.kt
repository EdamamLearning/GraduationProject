package ru.edamamlearning.graduationproject.data.network.model

import com.google.gson.annotations.SerializedName
import ru.edamamlearning.graduationproject.data.network.model.networkmodelinnerclasses.NetworkHint
import ru.edamamlearning.graduationproject.data.network.model.networkmodelinnerclasses.NetworkLinks
import ru.edamamlearning.graduationproject.data.network.model.networkmodelinnerclasses.NetworkParsed

data class NetworkModel(
    @SerializedName("_links")
    val links: NetworkLinks?,
    val hints: List<NetworkHint>?,
    val parsed: List<NetworkParsed>?,
    val text: String?
)