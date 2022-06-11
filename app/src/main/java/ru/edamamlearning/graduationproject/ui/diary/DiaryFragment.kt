package ru.edamamlearning.graduationproject.ui.diary

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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
import ru.edamamlearning.graduationproject.databinding.FragmentDiaryBinding
import ru.edamamlearning.graduationproject.di.viewmodelsfactory.ViewModelFactory
import ru.edamamlearning.graduationproject.utils.ToolbarApp
import javax.inject.Inject

class DiaryFragment : BaseFragment(R.layout.fragment_diary) {

    @Inject

    lateinit var vmFactory: ViewModelFactory
    private val viewModel: DiaryViewModel by lazy {
        ViewModelProvider(this, vmFactory)[DiaryViewModel::class.java]
    }
    private val binding: FragmentDiaryBinding by viewBinding()
    private val adapter by lazy {
        DiaryFragmentAdapter(
            isFoodChoice = viewModel::isFoodChoice,
            diaryClickHandler = viewModel::diaryFoodClickHandler
        )
    }

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
                }
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
            title = "Ежедневник",
            visibleRight = true,
            visibleLeft = false
        )

        (requireActivity().findViewById<AppCompatImageView>(R.id.info)).setImageResource(R.drawable.ic_info)
        (requireActivity().findViewById<AppCompatImageView>(R.id.back)).setImageResource(R.drawable.ic_toolbar_back_button)

        (requireActivity().findViewById<View>(R.id.info) as ImageView).setOnClickListener {
            Toast.makeText(context, "info", Toast.LENGTH_SHORT).show()
        }
    }
}
