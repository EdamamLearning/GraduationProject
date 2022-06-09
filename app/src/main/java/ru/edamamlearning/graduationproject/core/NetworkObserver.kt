package ru.edamamlearning.graduationproject.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.ref.WeakReference
import javax.inject.Inject

class NetworkObserver @Inject constructor(_context: Context) {

    private val context = WeakReference(_context)
    private val connectivityManager = context.get()?.getSystemService<ConnectivityManager>()

    private val networkStatus = MutableStateFlow(false)

    fun networkIsAvailable(): StateFlow<Boolean> = networkStatus.asStateFlow()

    init {
        val request = NetworkRequest.Builder().build()
        connectivityManager?.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    networkStatus.value = true
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    networkStatus.value = false
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    networkStatus.value = false
                }
            }
        )
    }
}
