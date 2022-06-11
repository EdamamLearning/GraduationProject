package ru.edamamlearning.graduationproject.core.resourcesprovider

interface ResourcesProvider {

    fun getString(resourcesId: Int): String
}