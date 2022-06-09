package ru.edamamlearning.graduationproject.ui.food

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.edamamlearning.graduationproject.core.BaseViewModel
import ru.edamamlearning.graduationproject.domain.DomainRepository
import ru.edamamlearning.graduationproject.domain.model.FoodDomainModel
import timber.log.Timber
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private val domainRepository: DomainRepository
) : BaseViewModel() {

    private val _food = MutableLiveData<List<FoodDomainModel>>()
    val food: LiveData<List<FoodDomainModel>> = _food

    fun getFoodOfLabel(label: String) {
        tryLaunch {
            val foodList = domainRepository.getAllHistoryFoods().asReversed()
            if (label == "All types") {
                _food.value = foodList
            } else {
                _food.value = foodList.filter {
                    it.label.contains(label)
                }
            }
        }.catch { throwable ->
            Timber.e(throwable.message)
        }.start()
    }
}