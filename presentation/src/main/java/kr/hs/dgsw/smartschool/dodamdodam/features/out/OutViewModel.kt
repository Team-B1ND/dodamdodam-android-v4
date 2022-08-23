package kr.hs.dgsw.smartschool.dodamdodam.features.out

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.out.state.GetOutState
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OutViewModel @Inject constructor(
    private val outUseCases: OutUseCases
) : BaseViewModel() {

    private val _getOutState = MutableStateFlow<GetOutState>(GetOutState())
    val getOutState: StateFlow<GetOutState> = _getOutState

    private val isGetOutLoading = MutableLiveData<Boolean>()

    init {
        combineLoadingVariable(isGetOutLoading)
        getMyOutApplies()
    }

    private fun getMyOutApplies() {
        outUseCases.getOut(LocalDate.now().toString()).divideResult(
            isGetOutLoading,
            { outList -> _getOutState.value = GetOutState(outList = outList ?: emptyList()) },
            { error -> _getOutState.value = GetOutState(error = error ?: "외출 외박을 받아올 수 없습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    companion object {
        const val ON_CLICK_BACK = 0
    }
}
