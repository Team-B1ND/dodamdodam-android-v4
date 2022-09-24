package kr.hs.dgsw.smartschool.dodamdodam.features.itmap

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.state.GetAllCompaniesState
import kr.hs.dgsw.smartschool.domain.usecase.itmap.ItMapUseCases
import javax.inject.Inject

@HiltViewModel
class ItMapViewModel @Inject constructor(
    private val itMapUseCases: ItMapUseCases
) : BaseViewModel() {

    private val isGetAllCompanyLoading = MutableLiveData(false)

    private val _getAllCompaniesState = MutableStateFlow<GetAllCompaniesState>(GetAllCompaniesState())
    val getAllCompaniesState: StateFlow<GetAllCompaniesState> = _getAllCompaniesState

    init {
        combineLoadingVariable(isGetAllCompanyLoading)
    }

    fun getAllCompanies() {
        itMapUseCases.getAllCompanies(Unit).divideResult(
            isGetAllCompanyLoading,
            { data -> _getAllCompaniesState.value = GetAllCompaniesState(isUpdate = true, companies = data ?: emptyList()) },
            { error -> _getAllCompaniesState.value = GetAllCompaniesState(error = error ?: "회사 정보를 받아올 수 없습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }
}
