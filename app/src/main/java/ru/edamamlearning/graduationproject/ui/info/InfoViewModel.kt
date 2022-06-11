package ru.edamamlearning.graduationproject.ui.info

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

class InfoViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
) : BaseViewModel() {

    val infoFood: StateFlow<List<FoodDomainModel>> = domainRepository.getAllFavoriteFoods()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    private val _food = MutableLiveData<FoodDomainModel>()
    val food: LiveData<FoodDomainModel> = _food

    fun getFood(foodId: String) {
        tryLaunch {
            _food.value = domainRepository.getFoodModelById(foodId)
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
    }

    fun isAFoodChoice(foodDomainModel: FoodDomainModel): Boolean {
        return infoFood.value.contains(foodDomainModel)
    }

    fun isInfoFoodsEmpty(): Boolean = infoFood.value.isEmpty()

    fun infoFoodClickHandler(foodDomainModel: FoodDomainModel): Boolean {
        return when (isAFoodChoice(foodDomainModel)) {
            true -> {
                tryLaunch {
                    domainRepository.deleteFavoriteFood(foodDomainModel)
                }.catch { throwable ->
                    Timber.e(throwable.message)
                }.start()
                false
            }
            false -> {
                tryLaunch {
                    domainRepository.saveFavoriteFood(foodDomainModel)
                }.catch { throwable ->
                    Timber.e(throwable.message)
                }.start()
                true
            }
        }
    }
}