package ru.edamamlearning.graduationproject.ui.favorite

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

class FavoriteViewModel @Inject constructor(
    private var domainRepository: DomainRepository,
) : BaseViewModel() {

    val favoriteFood: StateFlow<List<FoodDomainModel>> =
        domainRepository.getAllFavoriteFoods()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

    fun isFavoriteFoodsEmpty(): Boolean = favoriteFood.value.isEmpty()

    fun isAFoodFavorite(foodDomainModel: FoodDomainModel): Boolean {
        return favoriteFood.value.contains(foodDomainModel)
    }

    fun favouriteFoodClickHandler(foodDomainModel: FoodDomainModel): Boolean {
        return when (isAFoodFavorite(foodDomainModel)) {
            true -> {
                viewModelScope.launch(Dispatchers.IO) {
                    domainRepository.deleteFavoriteFood(foodDomainModel)
                }
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