package ru.edamamlearning.graduationproject.utils.message

import androidx.annotation.ColorRes
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.edamamlearning.graduationproject.R

class SystemMessageNotifier {

    val notifier = MutableSharedFlow<SystemMessage>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun send(message: SystemMessage) {
        notifier.tryEmit(message)
    }

    fun sendToast(message: String) {
        notifier.tryEmit(SystemMessage.Toast(message))
    }

    fun sendSnack(
        message: String,
        @ColorRes colorRes: Int = R.color.shadow_grey,
        duration: Int = Snackbar.LENGTH_LONG,
        dismissSnackBar: Flow<Unit>? = null
    ) {
        notifier.tryEmit(
            SystemMessage.Snack(
                text = message,
                colorRes = colorRes,
                snackBarDuration = duration,
                dismissSnackBar = dismissSnackBar
            )
        )
    }
}