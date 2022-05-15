package ru.edamamlearning.graduationproject.core

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import by.kirich1409.viewbindingdelegate.ViewBindingProperty
import by.kirich1409.viewbindingdelegate.viewBinding

inline fun <reified T : ViewBinding> Fragment.viewBinding(): ViewBindingProperty<Fragment, T> {
    return viewBinding()
}