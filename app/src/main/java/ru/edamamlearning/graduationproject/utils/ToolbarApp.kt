package ru.edamamlearning.graduationproject.utils

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import ru.edamamlearning.graduationproject.R

class ToolbarApp {
    fun setToolBar(
        activity: Activity,
        title: String,
        visibleRight: Boolean,
        visibleLeft: Boolean
    ) {
        (activity.findViewById<View>(R.id.toolbar_title) as TextView).text = title
        (activity.findViewById<View>(R.id.info)).isVisible = visibleRight
        (activity.findViewById<View>(R.id.back)).isVisible = visibleLeft
    }
}

