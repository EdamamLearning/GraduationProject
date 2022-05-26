package ru.edamamlearning.graduationproject.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    // private val getFoodUseCase: GetFoodUseCase
) : BaseViewModel() {

    private val _food = MutableLiveData<List<FoodDomainModel>>()
    val food: LiveData<List<FoodDomainModel>> = _food

    /*fun getFood(food: String) {
        viewModelScope.launch {
            _food.value = getFoodUseCase.execute(food)
        }
    }*/
}