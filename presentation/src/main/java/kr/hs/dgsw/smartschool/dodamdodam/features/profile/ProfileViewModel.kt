package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.profile.point.MyBonusPointState
import kr.hs.dgsw.smartschool.dodamdodam.features.profile.point.MyMinusPointState
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.point.GetMyPoint
import kr.hs.dgsw.smartschool.domain.usecase.point.PointUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases,
    private val pointUseCases: PointUseCases
) : BaseViewModel() {

    private val _myInfoState = MutableStateFlow(MyInfoState(isLoading = false))
    val myInfoState: StateFlow<MyInfoState> = _myInfoState

    private val _myBonusPointState = MutableStateFlow(MyBonusPointState(isLoading = false))
    val myBonusPointState: StateFlow<MyBonusPointState> = _myBonusPointState

    private val _myMinusPointState = MutableStateFlow(MyMinusPointState(isLoading = false))
    val myMinusPointState: StateFlow<MyMinusPointState> = _myMinusPointState

    private val _dormitorySelected = MutableLiveData<Boolean>(true)
    val dormitorySelected: LiveData<Boolean> get() = _dormitorySelected

    private val _schoolSelected = MutableLiveData<Boolean>(false)
    val schoolSelected: LiveData<Boolean> get() = _schoolSelected

    private val isGetMyInfoLoading = MutableLiveData(false)
    private val isGetMyBonusLoading = MutableLiveData(false)
    private val isGetMyMinusLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isGetMyBonusLoading, isGetMyMinusLoading, isGetMyInfoLoading)
        getMyInfo()
        getMyBonusPoint()
        getMyMinusPoint()
    }

    fun getMyInfo() {
        memberUseCases.getMyInfo(Unit).divideResult(
            isGetMyInfoLoading,
            { _myInfoState.value = MyInfoState(myInfo = it) },
            { _myInfoState.value = MyInfoState(error = it ?: "프로필 정보를 받아오지 못하였습니다.") },
        ).launchIn(viewModelScope)
    }

    private fun getMyBonusPoint() {
        pointUseCases.getMyPoint(
            GetMyPoint.Params(
                LocalDate.now().year.toString(),
                1
            )
        ).divideResult(
            isGetMyBonusLoading,
            { _myBonusPointState.value = MyBonusPointState(bonusPoint = it) },
            { _myBonusPointState.value = MyBonusPointState(error = it ?: "상벌점 조회를 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun getMyMinusPoint() {
        pointUseCases.getMyPoint(
            GetMyPoint.Params(LocalDate.now().year.toString(), 2)
        ).divideResult(
            isGetMyMinusLoading,
            { _myMinusPointState.value = MyMinusPointState(minusPoint = it) },
            { _myMinusPointState.value = MyMinusPointState(error = it ?: "상벌점 조회를 실패했습니다.") }
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

    companion object {
        const val EVENT_CHANGE_SELECTED = 1
        const val EVENT_GO_EDIT_PROFILE = 2
        const val EVENT_ON_CLICK_BUS = 3
        const val EVENT_ON_CLICK_SETTING = 4
    }
}
