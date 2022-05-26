package ru.edamamlearning.graduationproject.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.data.repository.DomainRepository
import ru.edamamlearning.graduationproject.domain.room.FoodEntity
import timber.log.Timber
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private var foodRepository: DomainRepository
//     private val getFoodUseCase: GetFoodUseCase
) : BaseViewModel() {

    private val _food = MutableLiveData<List<FoodEntity>>()
    val food: LiveData<List<FoodEntity>> = _food

    fun getFood() {
        tryLaunch {
            _food.value = foodRepository.getSavedFood().asReversed()
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