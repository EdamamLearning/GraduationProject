package ru.edamamlearning.graduationproject.ui.food

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.core.view.forEach
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentFoodBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import javax.inject.Inject

class FoodFragment : BaseFragment(R.layout.fragment_food) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: FoodViewModel by lazy {
        ViewModelProvider(this, vmFactory)[FoodViewModel::class.java]
    }
    private val binding: FragmentFoodBinding by viewBinding()

    private val adapter by lazy {
        FoodFragmentAdapter(
            onHistoryItemClicked = this::navigate
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        val observer = Observer<List<FoodDomainModel>> { listFood ->
            adapter.submitList(listFood)
        }
        viewModel.food.observe(viewLifecycleOwner, observer)
        setupChipGroup()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFoodOfLabel("All types")
    }

    private fun navigate(foodDomainModel: FoodDomainModel) {
        val action = FoodFragmentDirections
            .actionHomeFragmentToInfoFragment2(foodDomainModel.foodId)
        findNavController().navigate(action)
    }

    private fun setupChipGroup() {
        binding.chipGroup.forEach {
            val chip = it as Chip
            chip.setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->
                compoundButton as Chip
                if (isChecked) {
                    val chipText = chip.text.toString()
                    viewModel.getFoodOfLabel(chipText)
                }
            }
        }
    }
}