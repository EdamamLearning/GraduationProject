package ru.edamamlearning.graduationproject.ui.info

import androidx.lifecycle.ViewModel
import ru.edamamlearning.graduationproject.domain.GetFoodUseCase
import javax.inject.Inject

class InfoViewModel @Inject constructor(
    private val getFoodUseCase: GetFoodUseCase
) : ViewModel() {

}