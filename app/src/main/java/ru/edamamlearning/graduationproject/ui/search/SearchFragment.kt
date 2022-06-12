package ru.edamamlearning.graduationproject.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
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
import ru.edamamlearning.graduationproject.utils.ModalDialogInfo
import ru.edamamlearning.graduationproject.utils.ToolbarApp
import ru.edamamlearning.graduationproject.utils.extensions.saveNavigate
import ru.edamamlearning.graduationproject.utils.hideKeyboard
import javax.inject.Inject

class SearchFragment : BaseFragment(R.layout.fragment_search) {

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
            isFoodChoice = viewModel::isFoodChoise,
            favouriteClickHandler = viewModel::favouriteFoodClickHandler,
            diaryClickHandler = viewModel::diaryFoodClickHandler,

            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        binding.emptySearchLayout.isInvisible = adapter.itemCount != 0
        setQueryListener()
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
                        this@SearchFragment.hideKeyboard()
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

    override fun onResume() {
        super.onResume()
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = ToolbarApp()

        toolbar.setToolBar(
            activity = requireActivity(),
            title = "Поиск",
            visibleRight = true,
            visibleLeft = false
        )

        (requireActivity().findViewById<AppCompatImageView>(R.id.info)).setImageResource(R.drawable.ic_info)
        (requireActivity().findViewById<AppCompatImageView>(R.id.back)).setImageResource(R.drawable.ic_toolbar_back_button)

        (requireActivity().findViewById<View>(R.id.info) as ImageView).setOnClickListener {
            val modalBottomSheet = ModalDialogInfo()
            val bundle = Bundle()
            bundle.putString("title", getString(R.string.info_app))
            bundle.putString("message", getString(R.string.info_user))
            bundle.putInt("btnCount", 1)
            bundle.putString("btnTextFirst", getString(R.string.ok))
            bundle.putString("btnTextSecond", "")
            modalBottomSheet.arguments = bundle
            modalBottomSheet.show(parentFragmentManager, "ExitProfileDialogFragment")
        }
    }


}

