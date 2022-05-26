package ru.edamamlearning.graduationproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentFavoriteBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import javax.inject.Inject

class FavoriteFragment : BaseFragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this, vmFactory)[FavoriteViewModel::class.java]
    }

    private val adapter by lazy {
        FavoriteFragmentAdapter(
            isFavorite = viewModel::isAFoodFavorite,
            favouriteClickHandler = viewModel::favouriteFoodClickHandler
        )
    }

    private val binding: FragmentFavoriteBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        if (viewModel.isFavoriteFoodsEmpty()) {
            binding.emptyFavoritesLayout.visibility = View.GONE
        }
        lifecycleScope.launchWhenStarted {
            viewModel.favoriteFood
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest {
                    adapter.submitList(it)
                }
        }
    }
}