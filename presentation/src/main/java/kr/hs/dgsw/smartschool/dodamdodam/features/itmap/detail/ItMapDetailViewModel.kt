package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail.state.GetCompanyByIdState
import kr.hs.dgsw.smartschool.domain.usecase.itmap.ItMapUseCases
import javax.inject.Inject

@HiltViewModel
class ItMapDetailViewModel @Inject constructor(
    private val itMapUseCases: ItMapUseCases
) : BaseViewModel() {

    private val _getCompanyByIdState = MutableStateFlow<GetCompanyByIdState>(GetCompanyByIdState())
    val getCompanyByIdState: StateFlow<GetCompanyByIdState> = _getCompanyByIdState

    fun getDetailCompanyInfo(id: Int) {
        itMapUseCases.getCompanyById(id).divideResult(
            isLoading,
            { company -> _getCompanyByIdState.value = GetCompanyByIdState(company = company) },
            { error -> _getCompanyByIdState.value = GetCompanyByIdState(error = error ?: "회사 정보를 받아올 수 없습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }
}
