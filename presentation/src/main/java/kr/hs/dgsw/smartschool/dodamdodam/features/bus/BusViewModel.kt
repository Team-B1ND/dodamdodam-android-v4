package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
): BaseViewModel() {
    private val _busState = MutableStateFlow(BusState(isLoading = false))
    val busState: StateFlow<BusState> = _busState

    val hasBus = MutableLiveData<Boolean>(false)

    init {
        getBusList()
    }

    private fun getBusList(){
        busUseCases.getBus().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _busState.value = BusState(busList = result.data ?: emptyList<BusByDate>())
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    _busState.value = BusState(isLoading = true)
                    isLoading.value = true
                }
                is Resource.Error -> {
                    _busState.value = BusState(error = result.message ?: "버스를 받아오지 못하였습니다.")
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}
