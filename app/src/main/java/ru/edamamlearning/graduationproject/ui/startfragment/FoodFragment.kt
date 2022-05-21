package ru.edamamlearning.graduationproject.ui.startfragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.application.App
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentFoodBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import javax.inject.Inject

class FoodFragment : BaseFragment(R.layout.fragment_food) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: FoodViewModel by lazy {
        ViewModelProvider(this, vmFactory)[FoodViewModel::class.java]
    }
    private val binding: FragmentFoodBinding by viewBinding()

    private val adapter by lazy {
        FoodFragmentAdapter()
    }

    override fun onAttach(context: Context) {
        App.instance.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sfRv.adapter = adapter
        /*viewModel.getFood("egg")
        lifecycleScope.launchWhenCreated {
            viewModel.food
                .collectLatest {


                }
        }*/

        lifecycleScope.launchWhenStarted {
            viewModel.getFood("")
            viewModel.food
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    Log.d("ResponseDomainModel", "text = $it")
                    adapter.setData(it)
                }
        }
    }
}