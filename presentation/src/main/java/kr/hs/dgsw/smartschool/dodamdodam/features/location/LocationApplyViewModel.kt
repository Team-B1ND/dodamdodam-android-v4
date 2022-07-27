package kr.hs.dgsw.smartschool.dodamdodam.features.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.time.TimeUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class LocationApplyViewModel @Inject constructor(
    private val timeUseCases: TimeUseCases
) : BaseViewModel() {

    private val _currentTime = MutableLiveData<Int>()
    val currentTime: LiveData<Int> get() = _currentTime

    private val _getAllTimeState = MutableStateFlow<GetAllTimeState>(GetAllTimeState())
    val getAllTimeState: StateFlow<GetAllTimeState> = _getAllTimeState

    init {
        getTimeTable()
    }

    fun setCurrentTime(time: Int) {
        _currentTime.value = time
    }

    private fun getTimeTable() {
        timeUseCases.getAllTime().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isLoading.value = false
                    _getAllTimeState.value = GetAllTimeState(timeTable = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    isLoading.value = true
                }
                is Resource.Error -> {
                    isLoading.value = false
                    _getAllTimeState.value = GetAllTimeState(error = result.message ?: "시간을 받아오지 못하였습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }

}