package ru.edamamlearning.graduationproject.data

import ru.edamamlearning.graduationproject.data.network.model.NetworkModel


interface NetworkRepository {

    suspend fun get(text: String): NetworkModel
}