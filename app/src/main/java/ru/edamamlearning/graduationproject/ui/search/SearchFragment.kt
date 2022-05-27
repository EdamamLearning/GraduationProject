package ru.edamamlearning.graduationproject.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.NetworkObserver
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentSearchBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import javax.inject.Inject

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    @Inject
    lateinit var networkObserver: NetworkObserver

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this, vmFactory)[SearchViewModel::class.java]
    }
    private val binding: FragmentSearchBinding by viewBinding()
    private val adapter by lazy {
        SearchAdapter(
            onFavouriteItemClicked = this::navigate,
            isFavorite = viewModel::isAFoodFavorite,
            favouriteClickHandler = viewModel::favouriteFoodClickHandler,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        binding.emptySearchLayout.isInvisible = adapter.itemCount != 0
        setQueryListener()
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            networkObserver.networkIsAvailable()
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest { isConnection ->
                    if(!isConnection){
                        findNavController().navigate(R.id.action_searchFragment_to_disconnectDialog)
                    }
                }
        }
    }

    private fun navigate(foodDomainModel: FoodDomainModel) {
        val action = SearchFragmentDirections
            .actionSearchFragmentToInfoFragment(foodDomainModel.foodId)
        findNavController().navigate(action)
    }

    private fun setQueryListener() {
        binding.searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                toggleLoader(true)
                val query = binding.searchEditText.text.toString()
                if (query.isNotBlank()) {
                    binding.emptySearchLayout.visibility = View.INVISIBLE
                    lifecycleScope.launchWhenStarted {
                        viewModel.getFood(query)
                        viewModel.food.observe(viewLifecycleOwner) { items ->
                            toggleLoader(false)
                            items.let {
                                adapter.submitList(it)
                            }
                        }
                    }
                    return@OnEditorActionListener true
                } else {
                    toggleLoader(false)
                    Toast.makeText(
                        context,
                        getString(R.string.enter_search_word),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@OnEditorActionListener false
                }
            }
            false
        })
    }

    private fun setRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun toggleLoader(loading: Boolean) {
        binding.progressBar.isVisible = loading
    }
}