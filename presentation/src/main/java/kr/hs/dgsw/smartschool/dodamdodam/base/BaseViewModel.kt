package kr.hs.dgsw.smartschool.dodamdodam.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event

open class BaseViewModel : ViewModel() {

    protected val isLoading: MediatorLiveData<Boolean> = MediatorLiveData()
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>>
        get() = _viewEvent

    fun viewEvent(content: Any) {
        _viewEvent.value = Event(content)
    }

    fun combineLoadingVariable(vararg lives: MutableLiveData<Boolean>) {
        lives.forEach { liveData ->
            isLoading.addSource(liveData) { isLoading.value = lives.any { it.value == true } }
        }
    }

    val onErrorEvent = MutableLiveData<Throwable>()
}