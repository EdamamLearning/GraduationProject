package ru.edamamlearning.graduationproject.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentInfoBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import javax.inject.Inject

class InfoFragment : BaseFragment(R.layout.fragment_info) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: InfoViewModel by lazy {
        ViewModelProvider(this, vmFactory)[InfoViewModel::class.java]
    }
    private val navigationArgs: InfoFragmentArgs by navArgs()
    private val binding: FragmentInfoBinding by viewBinding()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.setShowHideAnimationEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodId = navigationArgs.foodId
    }
}