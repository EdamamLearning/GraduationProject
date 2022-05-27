package ru.edamamlearning.graduationproject.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import timber.log.Timber
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private val domainRepository: DomainRepository
//     private val getFoodUseCase: GetFoodUseCase
) : BaseViewModel() {

    private val _food = MutableLiveData<List<FoodDomainModel>>()
    val food: LiveData<List<FoodDomainModel>> = _food

    val favoriteFood: StateFlow<List<FoodDomainModel>> =
        domainRepository.getAllFavoriteFoods()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

    fun getFood() {
        tryLaunch {
            _food.value = domainRepository.getAllHistoryFoods().asReversed()
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
    }

    /*fun getFood(food: String) {
        viewModelScope.launch {
            _food.value = getFoodUseCase.execute(food)
        }
    }*/
}