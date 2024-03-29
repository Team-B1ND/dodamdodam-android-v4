package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.profile.point.GetMyYearPointsState
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.point.GetMyYearPointsUseCase
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases,
    private val getMyYearPointsUseCase: GetMyYearPointsUseCase
) : BaseViewModel() {

    private val _myInfoState = MutableStateFlow(MyInfoState(isLoading = false))
    val myInfoState: StateFlow<MyInfoState> = _myInfoState

    private val _getMyYearPointState = MutableStateFlow(GetMyYearPointsState())
    val getMyYearPointsState: StateFlow<GetMyYearPointsState> = _getMyYearPointState

    private val _dormitorySelected = MutableLiveData(true)
    val dormitorySelected: LiveData<Boolean> get() = _dormitorySelected

    private val _schoolSelected = MutableLiveData(false)
    val schoolSelected: LiveData<Boolean> get() = _schoolSelected

    private val isGetMyInfoLoading = MutableLiveData(false)
    private val isGetMyBonusLoading = MutableLiveData(false)
    private val isGetMyMinusLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isGetMyBonusLoading, isGetMyMinusLoading, isGetMyInfoLoading)
        getMyInfo()
        getMyYearPoint()
    }

    fun getMyInfo() {
        memberUseCases.getMyInfo().divideResult(
            isGetMyInfoLoading,
            { _myInfoState.value = MyInfoState(myInfo = it) },
            { _myInfoState.value = MyInfoState(error = it ?: "프로필 정보를 받아오지 못하였습니다.") },
        ).launchIn(viewModelScope)
    }

    private fun getMyYearPoint() {
        getMyYearPointsUseCase(GetMyYearPointsUseCase.Params(LocalDate.now().year)).divideResult(
            isGetMyBonusLoading,
            { _getMyYearPointState.value = GetMyYearPointsState(isReach = true, yearPointList = it ?: emptyList()) },
            { _getMyYearPointState.value = GetMyYearPointsState(error = it ?: "상벌점 조회를 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickEditProfile() {
        viewEvent(EVENT_GO_EDIT_PROFILE)
    }

    fun selectDormitory() {
        _dormitorySelected.value = true
        _schoolSelected.value = false
        viewEvent(EVENT_CHANGE_SELECTED)
    }

    fun selectSchool() {
        _dormitorySelected.value = false
        _schoolSelected.value = true
        viewEvent(EVENT_CHANGE_SELECTED)
    }

    fun onClickBus() {
        viewEvent(EVENT_ON_CLICK_BUS)
    }

    fun onClickSetting() {
        viewEvent(EVENT_ON_CLICK_SETTING)
    }

    fun onClickLostFound() {
        viewEvent(EVENT_ON_CLICK_LOST_FOUND)
    }

    companion object {
        const val EVENT_CHANGE_SELECTED = 1
        const val EVENT_GO_EDIT_PROFILE = 2
        const val EVENT_ON_CLICK_BUS = 3
        const val EVENT_ON_CLICK_SETTING = 4
        const val EVENT_ON_CLICK_LOST_FOUND = 5
    }
}
