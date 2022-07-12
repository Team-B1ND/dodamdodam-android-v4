package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.usecase.bus.BusUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BusViewModel @Inject constructor(
    private val busUseCases: BusUseCases
): BaseViewModel() {
    private val _busState = MutableStateFlow(BusState(isLoading = false))
    val busState: StateFlow<BusState> = _busState

    fun getBusList(date: Date){
        busUseCases.getBus(
        ).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _busState.value = BusState(busList = result.data ?: emptyList<BusByDate>())
                }
                is Resource.Loading -> {
                    _busState.value = BusState(isLoading = true)
                }
                is Resource.Error -> {
                    _busState.value = BusState(error = result.message ?: "급식을 받아오지 못하였습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }
}
