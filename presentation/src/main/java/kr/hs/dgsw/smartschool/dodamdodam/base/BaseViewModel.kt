package kr.hs.dgsw.smartschool.dodamdodam.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.widget.Event
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils

open class BaseViewModel : ViewModel() {
    protected val isLoading: MediatorLiveData<Boolean> = MediatorLiveData()
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    val tokenErrorEvent = MutableLiveData<String>()

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

    fun <T> Flow<Resource<T>>.divideResult(
        isLoading: MutableLiveData<Boolean>,
        successAction: (T?) -> Unit,
        errorAction: (String?) -> Unit
    ) = onEach { resource ->
        when (resource) {
            is Resource.Success -> {
                isLoading.value = false
                successAction.invoke(resource.data)
            }
            is Resource.Loading -> {
                isLoading.value = true
            }
            is Resource.Error -> {
                isLoading.value = false
                if (resource.message == Utils.TOKEN_EXCEPTION) {
                    tokenErrorEvent.value = resource.message ?: "세션이 만료되었습니다."
                } else {
                    errorAction.invoke(resource.message)
                }
            }
        }
    }
}
