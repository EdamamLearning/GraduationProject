package ru.edamamlearning.graduationproject.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.edamamlearning.graduationproject.domain.GetFoodUseCase
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val getFoodUseCase: GetFoodUseCase
) : ViewModel() {

    private val _food = MutableLiveData<List<FoodDomainModel>>()
    val food: LiveData<List<FoodDomainModel>> = _food

    fun getFood(food: String) {
        viewModelScope.launch {
            _food.value = getFoodUseCase.execute(food)
        }
    }
}