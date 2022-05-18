package ru.edamamlearning.graduationproject.ui.startfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.edamamlearning.graduationproject.domain.GetFoodUseCase
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import javax.inject.Inject

class StartFragmentViewModel @Inject constructor(
    private val getFoodUseCase: GetFoodUseCase
) : ViewModel() {

    private val _food = MutableStateFlow<FoodDomainModel>(FoodDomainModel())
    val food: StateFlow<FoodDomainModel> = _food.asStateFlow()

    fun getFood(food: String){
        viewModelScope.launch {
            getFoodUseCase.execute(food)
                .distinctUntilChanged()
                .collectLatest {
                    _food.value = it
                }
        }
    }
}