package ru.edamamlearning.graduationproject.ui.food

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ru.edamamlearning.graduationproject.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sfRv.adapter = adapter

       /* lifecycleScope.launchWhenStarted {
            viewModel.getFood("")
            viewModel.food.observe(viewLifecycleOwner) { items ->
                items.let {
                    adapter.submitList(it)
                }
            }
        }*/
    }
}