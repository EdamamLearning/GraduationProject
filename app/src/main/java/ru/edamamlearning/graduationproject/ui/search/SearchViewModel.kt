package ru.edamamlearning.graduationproject.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private var domainRepository: DomainRepository
) : BaseViewModel() {

    private val _food = MutableLiveData<List<FoodDomainModel>>()
    val food: LiveData<List<FoodDomainModel>> = _food

    fun getFood(food: String) {
        tryLaunch {
            _food.value = domainRepository.getFoodModel(food)
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
    }
}