package ru.edamamlearning.graduationproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.core.resourcesprovider.ResourcesProvider
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.DiaryFoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.message.SystemMessageNotifier
import timber.log.Timber
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private var domainRepository: DomainRepository,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {

    private var snackDismissFlow: MutableSharedFlow<Unit>? = null
    private val _food = MutableLiveData<List<FoodDomainModel>>()
    val food: LiveData<List<FoodDomainModel>> = _food
    val favoriteFood: StateFlow<List<FoodDomainModel>> =
        domainRepository.getAllFavoriteFoods()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

    fun getFood(food: String) {
        tryLaunch {
            _food.value = domainRepository.getFoodModel(food)
        }.catch { throwable ->
            systemMessageNotifier.sendSnack(
                message = resourcesProvider.getString(R.string.error),
                colorRes = R.color.support_303
            )
            Timber.e(throwable.message)
        }.start()
    }

    fun isAFoodFavorite(foodDomainModel: FoodDomainModel): Boolean {
        return favoriteFood.value.contains(foodDomainModel)
    }

    fun favouriteFoodClickHandler(foodDomainModel: FoodDomainModel): Boolean {

        return when (isAFoodFavorite(foodDomainModel)) {
            true -> {
                tryLaunch {
                    domainRepository.deleteFavoriteFood(foodDomainModel)
                    deleteFavoriteFood()
                }.catch { throwable ->
                    Timber.e(throwable.message)
                }.start()
                false
            }
            false -> {
                tryLaunch {
                    domainRepository.saveFavoriteFood(foodDomainModel)
                    addFavoriteFood()
                }.catch { throwable ->
                    Timber.e(throwable.message)
                }.start()
                true
            }
        }
    }

    private fun addFavoriteFood() {
        snackDismissFlow = MutableSharedFlow(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
        systemMessageNotifier.sendSnack(
            message = resourcesProvider.getString(R.string.favorite_food_added),
            colorRes = R.color.support_303,
            duration = Snackbar.LENGTH_SHORT,
            dismissSnackBar = snackDismissFlow
        )
    }

    private fun deleteFavoriteFood() {
        snackDismissFlow = MutableSharedFlow(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
        systemMessageNotifier.sendSnack(
            message = resourcesProvider.getString(R.string.favorite_food_delete),
            colorRes = R.color.support_303,
            duration = Snackbar.LENGTH_SHORT,
            dismissSnackBar = snackDismissFlow
        )
    }

    fun diaryFoodClickHandler(diaryFoodDomainModel: DiaryFoodDomainModel) {
        tryLaunch {
            domainRepository.saveDiaryFood(diaryFoodDomainModel)
            addFoodInDiary()
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
    }

    private fun addFoodInDiary() {
        snackDismissFlow = MutableSharedFlow(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
        systemMessageNotifier.sendSnack(
            message = resourcesProvider.getString(R.string.diary_food_added),
            colorRes = R.color.support_303,
            duration = Snackbar.LENGTH_SHORT,
            dismissSnackBar = snackDismissFlow
        )
    }

    fun isFavoriteFoodsEmpty(): Boolean = favoriteFood.value.isEmpty()
}