package kr.hs.dgsw.smartschool.dodamdodam.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.MealState
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event

open class BaseViewModel : ViewModel() {

    protected val isLoading: MutableLiveData<Boolean> = MediatorLiveData()
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>>
        get() = _viewEvent

    fun viewEvent(content: Any) {
        _viewEvent.value = Event(content)
    }

    val onErrorEvent = MutableLiveData<Throwable>()
}