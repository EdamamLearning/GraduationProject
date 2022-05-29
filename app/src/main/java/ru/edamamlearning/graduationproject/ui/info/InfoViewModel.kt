package ru.edamamlearning.graduationproject.ui.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import timber.log.Timber
import javax.inject.Inject

class InfoViewModel @Inject constructor(
    private val domainRepository: DomainRepository
) : BaseViewModel() {

    private val _food = MutableLiveData<FoodDomainModel>()
    val food: LiveData<FoodDomainModel> = _food

    fun getFood(foodId: String) {
        tryLaunch {
            _food.value = domainRepository.getFoodModelById(foodId)
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
    }
}