package ru.edamamlearning.graduationproject.ui.info

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentInfoBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.extensions.loadPicture
import ru.edamamlearning.graduationproject.utils.roundAp
import javax.inject.Inject


class InfoFragment : BaseFragment(R.layout.fragment_info) {

    interface OnInfoFragmentEvent {
        fun hideBottomBar()
        fun showBottomBar()
    }

    var onInfoFragmentEvent: OnInfoFragmentEvent? = null

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: InfoViewModel by lazy {
        ViewModelProvider(this, vmFactory)[InfoViewModel::class.java]
    }
    private val navigationArgs: InfoFragmentArgs by navArgs()
    private val binding: FragmentInfoBinding by viewBinding()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInfoFragmentEvent = try {
            activity as OnInfoFragmentEvent
        } catch (e: ClassCastException) {
            throw ClassCastException("$activity must implement onSomeEventListener")
        }
        onInfoFragmentEvent?.hideBottomBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodId = navigationArgs.foodId
        val observer = Observer<FoodDomainModel> {
            it?.let { renderData(it) }
        }
        viewModel.food.observe(viewLifecycleOwner, observer)
        viewModel.getFood(foodId)
    }

    private fun renderData(food: FoodDomainModel) {
        binding.apply {
            binding.foodImage.loadPicture(food.image)
            textView.text = food.label
            categoryLabel.text = food.categoryLabel
            category.text = food.category
            if (food.brand != NULL) {
                brand.text = food.brand
                brand.visibility = View.VISIBLE
                brandTitle.visibility = View.VISIBLE
            }
            if (food.foodContentsLabel != NULL) {
                foodContentsLabel.text = food.foodContentsLabel
                foodContentsLabel.visibility = View.VISIBLE
                contentsTitle.visibility = View.VISIBLE
            }
            if (food.servingsPerContainer != NULL) {
                serving.text = roundAp(food.servingsPerContainer)
                serving.visibility = View.VISIBLE
                servingTitle.visibility = View.VISIBLE
                scrollView.visibility = View.VISIBLE
            }
            caloriesCount.text = roundAp(food.nutrients.energyKCal)
            fiberCount.text = roundAp(food.nutrients.fiber)
            fatsCount.text = roundAp(food.nutrients.fat)
            carbohydrateCount.text = roundAp(food.nutrients.carbohydrate)
            proteinCount.text = roundAp(food.nutrients.protein)
        }
    }

    override fun onDetach() {
        super.onDetach()
        onInfoFragmentEvent?.showBottomBar()
    }

    companion object {
        const val NULL = ""
    }
}