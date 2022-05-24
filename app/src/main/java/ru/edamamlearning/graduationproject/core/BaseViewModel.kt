package ru.edamamlearning.graduationproject.core

import androidx.annotation.CheckResult
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import timber.log.Timber
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var contextProvider: CoroutineContextProvider

    /** Dispatchers.Main */
    private val mainScope by lazy { CoroutineScope(contextProvider.Main + SupervisorJob()) }

    @CheckResult
    protected fun tryLaunch(
        scope: CoroutineScope = mainScope,
        block: suspend CoroutineScope.() -> Unit,
    ): LaunchBuilder = LaunchBuilder(scope, block)

    protected inner class LaunchBuilder(
        private val scope: CoroutineScope,
        private val block: suspend CoroutineScope.() -> Any,
        private var errorCallback: ((Throwable) -> Unit)? = null,
        private var completionCallback: (() -> Unit)? = null,
    ) {

        @CheckResult
        fun catch(errorCallback: ((Throwable) -> Unit)): LaunchBuilder = apply {
            this.errorCallback = errorCallback
        }

        @CheckResult
        fun finally(completionCallback: (() -> Unit)): LaunchBuilder = apply {
            this.completionCallback = completionCallback
        }

        fun start(isSupervisor: Boolean = false): Job = scope.launch {
            try {
                if (isSupervisor) supervisorScope(block)
                else coroutineScope(block)
            } catch (t: Throwable) {
                logErrorResponse(t)
                when {
                    t is CancellationException -> Timber.d("Coroutine was cancelled")
                    errorCallback != null -> errorCallback?.invoke(t)
                    else -> {
                        Timber.e(t, "Unhandled exception")
                        throw t
                    }
                }
            }
        }.apply {
            invokeOnCompletion { completionCallback?.invoke() }
        }
    }

    protected fun logErrorResponse(it: Throwable) {
        Timber.e(it, this::class.java.simpleName)
    }
}