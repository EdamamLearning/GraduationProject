package ru.edamamlearning.graduationproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentFavoriteBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.ui.AppAdapter
import ru.edamamlearning.graduationproject.ui.AppViewModel
import ru.edamamlearning.graduationproject.ui.datepickerdialogfragment.DatePickerDialogFragment
import ru.edamamlearning.graduationproject.utils.extensions.toDiaryFoodDomainModel
import javax.inject.Inject

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this, vmFactory)[AppViewModel::class.java]
    }

    private val adapter by lazy {
        AppAdapter(
            onFavouriteItemClicked = this::navigate,
            isFavorite = viewModel::isAFoodFavorite,
            favouriteClickHandler = viewModel::favouriteFoodClickHandler,
            addDateToFood = this::diaryFoodHandler
        )
    }

    private val binding: FragmentFavoriteBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
    }

    private fun diaryFoodHandler(foodDomainModel: FoodDomainModel) {
        showDatePicker()
        setFragmentResultListener(DatePickerDialogFragment.REQUEST_KEY) { _, result: Bundle ->
            val array: IntArray = result.getIntArray(DatePickerDialogFragment.KEY_RESPONSE)
                ?: throw RuntimeException("DialogFragment result is null")
            val date = "${array[0]} ${array[1]} ${array[2]}"
            viewModel.diaryFoodClickHandler(foodDomainModel.toDiaryFoodDomainModel(date))
        }
    }

    private fun showDatePicker() {
        findNavController().navigate(R.id.action_favoritesFragment_to_datePickerDialogFragment)
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.favoriteFood
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest {
                    adapter.submitList(it)
                    checkFavorite()
                }
        }
    }

    private fun navigate(foodDomainModel: FoodDomainModel) {
        val action = FavoriteFragmentDirections
            .actionFavoritesFragmentToInfoFragment(foodDomainModel.foodId)
        findNavController().navigate(action)
    }

    private fun checkFavorite() {
        if (!viewModel.isFavoriteFoodsEmpty()) {
            binding.emptyFavoritesLayout.visibility = View.GONE
        }
    }
}