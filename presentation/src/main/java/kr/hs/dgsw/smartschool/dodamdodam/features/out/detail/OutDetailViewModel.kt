package kr.hs.dgsw.smartschool.dodamdodam.features.out.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.out.detail.state.GetOutDetailState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.usecase.out.OutUseCases
import javax.inject.Inject

@HiltViewModel
class OutDetailViewModel @Inject constructor(
    private val outUseCases: OutUseCases
) : BaseViewModel() {

    val outItem = MutableLiveData<OutItem>()

    val id = MutableLiveData<Int>()

    private val _getOutDetailState = MutableStateFlow<GetOutDetailState>(GetOutDetailState())
    val getOutDetailState: StateFlow<GetOutDetailState> = _getOutDetailState

    private val isGetOutSleepingByIdLoading = MutableLiveData(false)
    private val isGetOutGoingByIdLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isGetOutSleepingByIdLoading, isGetOutGoingByIdLoading)
    }

    fun getDetailOut(isOutSleeping: Boolean, id: Int) {
        if (isOutSleeping)
            getDetailOutSleeping(id)
        else
            getDetailOutGoing(id)
    }

    private fun getDetailOutSleeping(id: Int) {
        outUseCases.getOutSleepingById(id).divideResult(
            isGetOutSleepingByIdLoading,
            {
                _getOutDetailState.value = GetOutDetailState(outItem = it)
                outItem.value = it
            },
            { _getOutDetailState.value = GetOutDetailState(error = it ?: "외출/외박 상세정보를 받아올 수 없습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun getDetailOutGoing(id: Int) {
        outUseCases.getOutGoingById(id).divideResult(
            isGetOutGoingByIdLoading,
            {
                _getOutDetailState.value = GetOutDetailState(outItem = it)
                outItem.value = it
            },
            { _getOutDetailState.value = GetOutDetailState(error = it ?: "외출/외박 상세정보를 받아올 수 없습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickUpdate() {
        // TODO 외출외박 수정 화면으로 넘어가기
    }

}