package ru.edamamlearning.graduationproject.ui.diary

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

class DiaryViewModel @Inject constructor(
    private var domainRepository: DomainRepository
) : BaseViewModel() {

    val diaryFood: StateFlow<List<FoodDomainModel>> =
        domainRepository.getAllDiaryFoods()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

    fun isAFoodChoise(foodDomainModel: FoodDomainModel): Boolean {
        return diaryFood.value.contains(foodDomainModel)
    }

    fun isDiaryFoodsEmpty(): Boolean = diaryFood.value.isEmpty()
    fun diaryFoodClickHandler(foodDomainModel: FoodDomainModel): Boolean {
        return when (isAFoodChoise(foodDomainModel)) {
            true -> {
                viewModelScope.launch(Dispatchers.IO) {
                    domainRepository.deleteDiaryFood(foodDomainModel)
                }
                tryLaunch {
                    domainRepository.deleteDiaryFood(foodDomainModel)
                }.catch { throwable ->
                    Timber.e(throwable.message)
                }.start()
                false
            }
            false -> {
                tryLaunch {
                    domainRepository.saveDiaryFood(foodDomainModel)
                }.catch { throwable ->
                    Timber.e(throwable.message)
                }.start()
                true
            }
        }
    }
}