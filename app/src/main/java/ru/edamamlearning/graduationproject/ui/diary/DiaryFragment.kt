package ru.edamamlearning.graduationproject.ui.diary

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
import ru.edamamlearning.graduationproject.databinding.FragmentDiaryBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.domain.model.DiaryFoodDomainModel
import java.util.*
import javax.inject.Inject

class DiaryFragment : BaseFragment(R.layout.fragment_diary) {

    @Inject
    lateinit var vmFactory: ViewModelFactory
    private val viewModel: DiaryFragmentViewModel by lazy {
        ViewModelProvider(this, vmFactory)[DiaryFragmentViewModel::class.java]
    }
    private val binding: FragmentDiaryBinding by viewBinding()
    private val adapter by lazy {
        DiaryFragmentAdapter(
            onItemClicked = this::navigate,
            isFavorite = viewModel::isAFoodFavorite,
            favouriteClickHandler = viewModel::favouriteFoodClickHandler,
            deleteDiaryFood = viewModel::deleteDiaryFood,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.diaryRecyclerView.adapter = adapter
        binding.calendarView.date = GregorianCalendar().timeInMillis
        viewModel.getByDate(
            "${GregorianCalendar().get(GregorianCalendar.YEAR)}-" +
                    "${GregorianCalendar().get(GregorianCalendar.MONTH)}-" +
                    "${GregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH)}"
        )
        initCalendar()
    }

    private fun initCalendar() {
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            viewModel.getByDate("$year-$month-$dayOfMonth")
        }
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.diaryFood
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest {
                    adapter.submitList(it)
                }
        }
    }

    private fun navigate(diaryFoodDomainModel: DiaryFoodDomainModel) {
        val action = DiaryFragmentDirections
            .actionDairyFragmentToInfoFragment(diaryFoodDomainModel.foodId)
        findNavController().navigate(action)
    }
}
