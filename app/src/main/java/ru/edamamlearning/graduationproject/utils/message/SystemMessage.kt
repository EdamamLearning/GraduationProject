package ru.edamamlearning.graduationproject.utils.message

import androidx.annotation.ColorRes
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.Flow
import ru.edamamlearning.graduationproject.R

sealed class SystemMessage(val text: String) {
    class Alert(text: String) : SystemMessage(text)
    class Toast(text: String) : SystemMessage(text)
    class Snack(
        text: String,
        @ColorRes val colorRes: Int = R.color.shadow_grey,
        val snackBarDuration: Int = Snackbar.LENGTH_LONG,
        val dismissSnackBar: Flow<Unit>? = null
    ) : SystemMessage(text)
}