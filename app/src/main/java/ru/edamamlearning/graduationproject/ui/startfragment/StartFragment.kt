package ru.edamamlearning.graduationproject.ui.startfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import ru.edamamlearning.graduationproject.application.App
import ru.edamamlearning.graduationproject.databinding.FragmentStartBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import javax.inject.Inject

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding? = null")

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: StartFragmentViewModel by lazy {
        ViewModelProvider(this, vmFactory)[StartFragmentViewModel::class.java]
    }

    private val adapter by lazy {
        StartFragmentAdapter()
    }

    override fun onAttach(context: Context) {
        App.instance.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sfRv.adapter = adapter
        binding.sfEt.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                lifecycleScope.launchWhenStarted {
                    viewModel.getList(it.toString())
                        .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                        .collect {
                            Log.d("ResponseDomainModel", "text = ${it.text}")
                        }
                }
            }
        }
    }
}