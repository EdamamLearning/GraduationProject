package ru.edamamlearning.graduationproject.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentSearchBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.ui.AppAdapter
import ru.edamamlearning.graduationproject.ui.AppViewModel
import ru.edamamlearning.graduationproject.ui.datepickerdialogfragment.DatePickerDialogFragment
import ru.edamamlearning.graduationproject.utils.extensions.toDiaryFoodDomainModel
import ru.edamamlearning.graduationproject.utils.hideKeyboard
import javax.inject.Inject

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this, vmFactory)[AppViewModel::class.java]
    }
    private val binding: FragmentSearchBinding by viewBinding()
    private val adapter by lazy {
        AppAdapter(
            onItemClicked = this::navigate,
            isFavorite = viewModel::isAFoodFavorite,
            favouriteClickHandler = viewModel::favouriteFoodClickHandler,
            addDateToFood = this::diaryFoodHandler
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        binding.emptySearchLayout.isInvisible = adapter.itemCount != 0
        setQueryListener()
    }

    private fun diaryFoodHandler(foodDomainModel: FoodDomainModel) {
        showDatePicker()
        setFragmentResultListener(DatePickerDialogFragment.REQUEST_KEY) { _, result: Bundle ->
            val array: IntArray = result.getIntArray(DatePickerDialogFragment.KEY_RESPONSE)
                ?: throw RuntimeException("DialogFragment result is null")
            val date = "${array[0]}-${array[1]}-${array[2]}"
            viewModel.diaryFoodClickHandler(foodDomainModel.toDiaryFoodDomainModel(date))
        }
    }

    private fun showDatePicker() {
        findNavController().navigate(R.id.action_searchFragment_to_datePickerDialogFragment)
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
}

