package ru.edamamlearning.graduationproject.ui.startfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.application.App
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentStartBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import javax.inject.Inject

class StartFragment : BaseFragment(R.layout.fragment_start) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: StartFragmentViewModel by lazy {
        ViewModelProvider(this, vmFactory)[StartFragmentViewModel::class.java]
    }
    private val binding: FragmentStartBinding by viewBinding()

    private val adapter by lazy {
        StartFragmentAdapter()
    }

    override fun onAttach(context: Context) {
        App.instance.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sfRv.adapter = adapter
    }
}