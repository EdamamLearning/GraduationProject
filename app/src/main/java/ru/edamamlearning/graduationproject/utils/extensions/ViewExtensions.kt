package ru.edamamlearning.graduationproject.utils.extensions

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun Context.color(colorRes: Int): Int = ContextCompat.getColor(this, colorRes)

fun View.showSnackMessage(
    message: String,
    @ColorInt color: Int,
    duration: Int,
    dismissSnackBar: Flow<Unit>? = null,
    scope: CoroutineScope
) {
    val spannableString = SpannableStringBuilder().append(message)

    spannableString.setSpan(
        ForegroundColorSpan(Color.WHITE),
        0,
        message.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    val snackBar = Snackbar.make(this, spannableString, duration)
    snackBar.view.apply {
        setBackgroundColor(color)
        val params = layoutParams as? FrameLayout.LayoutParams
        params?.gravity = Gravity.TOP
        layoutParams = params

        val textView = findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.gravity = Gravity.CENTER
    }

    snackBar.addCallback(object : Snackbar.Callback() {

        override fun onShown(sb: Snackbar?) {
            super.onShown(sb)
            scope.launch {
                dismissSnackBar?.collect { snackBar.dismiss() }
            }
        }

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            snackBar.removeCallback(this)
            super.onDismissed(transientBottomBar, event)
        }
    })

    snackBar.show()
}