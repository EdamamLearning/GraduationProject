package ru.edamamlearning.graduationproject.ui.startfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.edamamlearning.graduationproject.domain.GetUseCase
import ru.edamamlearning.graduationproject.domain.model.DomainModel
import javax.inject.Inject

class StartFragmentViewModel @Inject constructor(
    private val getUseCase: GetUseCase
) : ViewModel() {

    fun getList(text: String): StateFlow<DomainModel> {
        return getUseCase.execute(text).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = DomainModel()
        )
    }
}