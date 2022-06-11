package ru.edamamlearning.graduationproject.core.resourcesprovider

import android.content.Context
import javax.inject.Inject

class ResourceProviderImpl
@Inject constructor(
    private val context: Context
) : ResourcesProvider {

    override fun getString(resourcesId: Int): String {
        return context.getString(resourcesId)
    }
}