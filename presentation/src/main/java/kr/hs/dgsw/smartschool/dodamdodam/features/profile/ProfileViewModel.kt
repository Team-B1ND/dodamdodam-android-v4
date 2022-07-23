package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.profile.point.MyDormitoryPointState
import kr.hs.dgsw.smartschool.dodamdodam.features.profile.point.MySchoolPointState
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.point.PointUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases,
    private val pointUseCases: PointUseCases
): BaseViewModel() {

    private val _myInfoState = MutableStateFlow(MyInfoState(isLoading = false))
    val myInfoState: StateFlow<MyInfoState> = _myInfoState

    private val _myDormitoryPointState = MutableStateFlow(MyDormitoryPointState(isLoading = false))
    val myDormitoryPointState: StateFlow<MyDormitoryPointState> = _myDormitoryPointState

    private val _mySchoolPointState = MutableStateFlow(MySchoolPointState(isLoading = false))
    val mySchoolPointState: StateFlow<MySchoolPointState> = _mySchoolPointState

    private val _dormitorySelected = MutableLiveData<Boolean>(true)
    val dormitorySelected: LiveData<Boolean> get() = _dormitorySelected

    private val _schoolSelected = MutableLiveData<Boolean>(false)
    val schoolSelected: LiveData<Boolean> get() = _schoolSelected

    init {
        getMyInfo()
        getMyDormitoryPoint()
        getMySchoolPoint()
    }

    fun getMyInfo() {
        memberUseCases.getMyInfo().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _myInfoState.value = MyInfoState(myInfo = result.data)
                }
                is Resource.Loading -> {
                    _myInfoState.value = MyInfoState(isLoading = true)
                }
                is Resource.Error -> {
                    _myInfoState.value = MyInfoState(
                        error = result.message ?: "프로필 정보를 받아오지 못하였습니다."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMyDormitoryPoint() {
        pointUseCases.getMyPointTarget(0).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _myDormitoryPointState.value = MyDormitoryPointState(myDormitoryPoint = result.data)
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    _myDormitoryPointState.value = MyDormitoryPointState(isLoading = true)
                    isLoading.value = true
                }
                is Resource.Error -> {
                    _myDormitoryPointState.value = MyDormitoryPointState(error = result.message ?: "상벌점 조회를 실패했습니다.")
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMySchoolPoint() {
        pointUseCases.getMyPointTarget(1).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _mySchoolPointState.value = MySchoolPointState(mySchoolPoint = result.data)
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    _mySchoolPointState.value = MySchoolPointState(isLoading = true)
                    isLoading.value = true
                }
                is Resource.Error -> {
                    _mySchoolPointState.value = MySchoolPointState(error = result.message ?: "상벌점 조회를 실패했습니다.")
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
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

    companion object {
        const val EVENT_CHANGE_SELECTED = 1
        const val EVENT_GO_EDIT_PROFILE = 2
    }
}