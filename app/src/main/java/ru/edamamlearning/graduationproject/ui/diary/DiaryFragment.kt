package ru.edamamlearning.graduationproject.ui.diary

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseFragment
import ru.edamamlearning.graduationproject.core.viewBinding
import ru.edamamlearning.graduationproject.databinding.FragmentDiaryBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import javax.inject.Inject

class DiaryFragment : BaseFragment(R.layout.fragment_diary) {

    @Inject

    lateinit var vmFactory: ViewModelFactory
    private val viewModel: DiaryViewModel by lazy {
        ViewModelProvider (this, vmFactory) [DiaryViewModel::class.java]
    }

    private val adapter by lazy {
        DiaryFragmentAdapter(
            isFoodChoise = viewModel::isFoodChoise,
            diaryClickHandler = viewModel::diaryFoodClickHandler
        )
    }

    private val binding: FragmentDiaryBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.diaryRecyclerView.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.diaryFood
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .distinctUntilChanged()
                .collectLatest {
                    adapter.submitList(it)
                    checkDiary()
                }
        }
    }

    private fun checkDiary() {
        if (!viewModel.isDiaryFoodsEmpty()) {
            binding.recyclerLayout.isVisible = !viewModel.isDiaryFoodsEmpty()
        }
    }



}
