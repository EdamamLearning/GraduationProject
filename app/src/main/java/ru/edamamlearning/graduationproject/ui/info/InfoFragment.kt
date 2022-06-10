package ru.edamamlearning.graduationproject.ui.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.*
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentInfoBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.ToolbarApp
import ru.edamamlearning.graduationproject.utils.extensions.loadPicture
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

    private val adapter by lazy {
        InfoFragmentAdapter(
            onFavouriteItemClicked = this::renderData,
            isAFoodChoice = viewModel::isAFoodChoice,
            infoClickHandler = viewModel::infoFoodClickHandler
        )
    }

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

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.infoFood
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest {
                    adapter.submitList(it)
                }
        }
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

    companion object {
        const val NULL = ""
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = ToolbarApp()

        toolbar.setToolBar(
            activity = requireActivity(),
            title = "Информация",
            visibleRight = true,
            visibleLeft = true
        )

        (requireActivity().findViewById<AppCompatImageView>(R.id.info)).setImageResource(R.drawable.ic_info)
        (requireActivity().findViewById<AppCompatImageView>(R.id.back)).setImageResource(R.drawable.ic_toolbar_back_button)

        (requireActivity().findViewById<View>(R.id.info) as ImageView).setOnClickListener {
            Toast.makeText(context, "info", Toast.LENGTH_SHORT).show()
        }

        (requireActivity().findViewById<View>(R.id.back) as ImageView).setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}