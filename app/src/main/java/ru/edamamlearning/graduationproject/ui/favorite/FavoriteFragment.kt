package ru.edamamlearning.graduationproject.ui.favorite

import android.os.Bundle
import android.view.View
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
import ru.edamamlearning.graduationproject.ui.AppViewModel
import ru.edamamlearning.graduationproject.ui.SearchAdapter
import ru.edamamlearning.graduationproject.ui.search.SearchFragmentDirections
import javax.inject.Inject

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this, vmFactory)[AppViewModel::class.java]
    }

    private val adapter by lazy {
        SearchAdapter(
            onFavouriteItemClicked = this::navigate,
            isFavorite = viewModel::isAFoodFavorite,
            isFoodChoice = viewModel::isFoodChoise,
            favouriteClickHandler = viewModel::favouriteFoodClickHandler,
            diaryClickHandler = viewModel::diaryFoodClickHandler,
            )
    }

    private val binding: FragmentFavoriteBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
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
        val action = SearchFragmentDirections
            .actionSearchFragmentToInfoFragment(foodDomainModel.foodId)
        findNavController().navigate(action)
    }

    private fun checkFavorite() {
        if (!viewModel.isFavoriteFoodsEmpty()) {
            binding.emptyFavoritesLayout.visibility = View.GONE
        }
    }
}