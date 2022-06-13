package ru.edamamlearning.graduationproject.ui.diary

import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import ru.edamamlearning.graduationproject.R
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.core.resourcesprovider.ResourcesProvider
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.DiaryFoodDomainModel
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import ru.edamamlearning.graduationproject.utils.message.SystemMessageNotifier
import timber.log.Timber
import javax.inject.Inject

class DiaryFragmentViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val systemMessageNotifier: SystemMessageNotifier,
    private val resourcesProvider: ResourcesProvider
) : BaseViewModel() {

    private var snackDismissFlow: MutableSharedFlow<Unit>? = null

    private val _diaryFood = MutableStateFlow<List<DiaryFoodDomainModel>>(emptyList())
    val diaryFood: StateFlow<List<DiaryFoodDomainModel>> = _diaryFood.asStateFlow()

    private val _sumNutrients = MutableStateFlow(SumNutrientsForDairy())
    val sumNutrients = _sumNutrients.asStateFlow()

    private val favoriteFood: StateFlow<List<FoodDomainModel>> =
        domainRepository.getAllFavoriteFoods()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

    fun refreshSumNutrients() {
        _sumNutrients.value = SumNutrientsForDairy()
    }

    private fun addNutrients(list: List<DiaryFoodDomainModel>) {
        val sum = SumNutrientsForDairy()
        list.forEach {
            sum.plus(
                _carbohydrate = it.carbohydrate.toDouble(),
                _energyKCal = it.energyKCal.toDouble(),
                _fat = it.fat.toDouble(),
                _fiber = it.fiber.toDouble(),
                _protein = it.protein.toDouble(),
            )
        }
        _sumNutrients.value = sum
    }

    fun getByDate(date: String) {
        tryLaunch {
            domainRepository.getDiaryFoodsByDate(date)
                .distinctUntilChanged()
                .collectLatest {
                    _diaryFood.value = it
                    addNutrients(it)
                }
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

    fun deleteDiaryFood(diaryFoodDomainModel: DiaryFoodDomainModel) {
        tryLaunch {
            domainRepository.deleteDiaryFood(diaryFoodDomainModel)
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
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
}

data class SumNutrientsForDairy(
    var carbohydrate: Double = 0.0,
    var energyKCal: Double = 0.0,
    var fat: Double = 0.0,
    var fiber: Double = 0.0,
    var protein: Double = 0.0,
) {
    fun plus(
        _carbohydrate: Double,
        _energyKCal: Double,
        _fat: Double,
        _fiber: Double,
        _protein: Double,
    ) {
        carbohydrate += _carbohydrate
        energyKCal += _energyKCal
        fat += _fat
        fiber += _fiber
        protein += _protein
    }
}