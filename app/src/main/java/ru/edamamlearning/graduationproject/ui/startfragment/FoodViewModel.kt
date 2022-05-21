package ru.edamamlearning.graduationproject.ui.startfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import ru.edamamlearning.graduationproject.domain.GetFoodUseCase
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private val getFoodUseCase: GetFoodUseCase
) : ViewModel() {

    private val _food = MutableStateFlow(FoodDomainModel())
    val food: StateFlow<FoodDomainModel> = _food.asStateFlow()

    fun getFood(food: String) {
        viewModelScope.launch {
            getFoodUseCase.execute(food)
                .distinctUntilChanged()
                .collectLatest {
                    _food.value = it
                }
        }
    }
}