package ru.edamamlearning.graduationproject.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentInfoBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.roundAp
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
        val observer = Observer<FoodDomainModel> {
            it?.let { renderData(it) }
        }
        viewModel.food.observe(viewLifecycleOwner, observer)
        viewModel.getFood(foodId)
    }

    private fun renderData(food: FoodDomainModel) {
        binding.apply {
            loadPicture(food.image)
            textView.text = food.label
            categoryLabel.text = food.categoryLabel
            category.text = food.category
            if (food.brand != NULL) {
                brand.text = food.brand
                brand.visibility = View.VISIBLE
                brandLabel.visibility = View.VISIBLE
            }
            if (food.foodContentsLabel != NULL) {
                foodContentsLabel.text = food.foodContentsLabel
                foodContentsLabel.visibility = View.VISIBLE
                foodContentsLabelLabel.visibility = View.VISIBLE
            }
            if (food.servingsPerContainer != NULL) {
                servingPerContainer.text = food.servingsPerContainer
                servingPerContainer.visibility = View.VISIBLE
                servingPerContainerLabel.visibility = View.VISIBLE
                scrollView.visibility = View.VISIBLE
            }
            energyKCal.text = roundAp(food.nutrients.energyKCal)
            fiber.text = roundAp(food.nutrients.fiber)
            fat.text = roundAp(food.nutrients.fat)
            carbohydrate.text = roundAp(food.nutrients.carbohydrate)
            protein.text = roundAp(food.nutrients.protein)
        }
    }

    private fun loadPicture(image: String?) {
        if (image != null) {
            Glide.with(binding.root)
                .load(image)
                .error(R.drawable.food_no_photo)
                .placeholder(R.drawable.food)
                .into(binding.foodImage)
        }
    }

    companion object {
        const val NULL = ""
    }
}