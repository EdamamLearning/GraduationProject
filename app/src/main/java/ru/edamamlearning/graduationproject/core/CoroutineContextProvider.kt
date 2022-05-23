package ru.edamamlearning.graduationproject.core

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@Suppress("PropertyName")
interface CoroutineContextProvider {

    val Main: CoroutineContext
    val IO: CoroutineContext
}

class CoroutineContextProviderImpl
@Inject constructor() : CoroutineContextProvider {

    override val Main: CoroutineContext
        get() = Dispatchers.Main.immediate

    override val IO: CoroutineContext
        get() = Dispatchers.IO
}