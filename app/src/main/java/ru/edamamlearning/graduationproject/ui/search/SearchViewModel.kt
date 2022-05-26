package ru.edamamlearning.graduationproject.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private var domainRepository: DomainRepository
) : BaseViewModel() {

    private val favoriteFood: StateFlow<List<FoodDomainModel>> = domainRepository.getAllFavoriteFoods()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

    private val _food = MutableLiveData<List<FoodDomainModel>>()
    val food: LiveData<List<FoodDomainModel>> = _food

    fun getFood(food: String) {
        tryLaunch {
            _food.value = domainRepository.getFoodModel(food)
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
    }

    fun isAFoodFavorite(foodDomainModel: FoodDomainModel): Boolean {
        return favoriteFood.value.contains(foodDomainModel)
    }

    fun favouriteFoodClickHandler(foodDomainModel: FoodDomainModel): Boolean {
        return when (isAFoodFavorite(foodDomainModel)) {
            true -> {
                viewModelScope.launch(Dispatchers.IO) {
                    domainRepository.deleteFavoriteFood(foodDomainModel)
                }
                false
            }
            false -> {
                viewModelScope.launch(Dispatchers.IO) {
                    domainRepository.saveFavoriteFood(foodDomainModel)
                }
                true
            }
        }
    }
}