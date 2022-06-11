package ru.edamamlearning.graduationproject.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
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
import ru.edamamlearning.graduationproject.utils.ToolbarApp
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

    private fun checkFavorite() {
        if (!viewModel.isFavoriteFoodsEmpty()) {
            binding.emptyFavoritesLayout.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = ToolbarApp()

        toolbar.setToolBar(
            activity = requireActivity(),
            title = "Избранное",
            visibleRight = true,
            visibleLeft = false
        )

        (requireActivity().findViewById<AppCompatImageView>(R.id.info)).setImageResource(R.drawable.ic_info)
        (requireActivity().findViewById<AppCompatImageView>(R.id.back)).setImageResource(R.drawable.ic_toolbar_back_button)
    }
}