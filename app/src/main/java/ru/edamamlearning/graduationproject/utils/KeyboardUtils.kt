package ru.edamamlearning.graduationproject.utils

import android.app.Activity
import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

/**
 * Скрывает виртуальную клавиатуру
 *
 * @param context текущий контекст
 * @param windowToken токен текущего представления, в котором присутсвует фокус на элемент
 */
fun hideKeyboard(context: Context, windowToken: IBinder) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Fragment.hideKeyboard() = activity?.hideKeyboard()

fun Activity.hideKeyboard() {
    ifNotNull(window.currentFocus, window.currentFocus?.windowToken) { focus, windowToken ->
        hideKeyboard(this, windowToken)
        focus.clearFocus()
    }
}

inline fun <A, B, R> ifNotNull(a: A?, b: B?, code: (A, B) -> R) {
    if (a != null && b != null) {
        code(a, b)
    }
}