package ru.edamamlearning.graduationproject.ui.startfragment

import androidx.lifecycle.ViewModel
import ru.edamamlearning.graduationproject.domain.GetFoodUseCase
import javax.inject.Inject

class StartFragmentViewModel @Inject constructor(
    private val getFoodUseCase: GetFoodUseCase
) : ViewModel() {


}