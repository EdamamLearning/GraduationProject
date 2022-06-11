package ru.edamamlearning.graduationproject.ui.diary

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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

    fun isFoodChoice(foodDomainModel: FoodDomainModel): Boolean {
        return diaryFood.value.contains(foodDomainModel)
    }

    fun diaryFoodClickHandler(foodDomainModel: FoodDomainModel): Boolean {
        return when (isFoodChoice(foodDomainModel)) {
            true -> {
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