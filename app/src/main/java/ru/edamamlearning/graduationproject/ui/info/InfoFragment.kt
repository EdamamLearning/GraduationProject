package ru.edamamlearning.graduationproject.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentInfoBinding

class InfoFragment : BaseFragment(R.layout.fragment_info) {

    private val binding: FragmentInfoBinding by viewBinding()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.setShowHideAnimationEnabled(false)
    }
}