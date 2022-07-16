package kr.hs.dgsw.smartschool.dodamdodam.features.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.profile.point.GetMyPointState
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

    private val _getMyPointState = MutableStateFlow(GetMyPointState(isLoading = false))
    val getMyPointState: StateFlow<GetMyPointState> = _getMyPointState

    init {
        getMyInfo()
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

    fun getMyPoint(year: String, type: Int) {
        pointUseCases.getMyPoint(year, type).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _getMyPointState.value = GetMyPointState(myPoint = result.data)
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    _getMyPointState.value = GetMyPointState(isLoading = true)
                    isLoading.value = true
                }
                is Resource.Error -> {
                    _getMyPointState.value = GetMyPointState(error = result.message ?: "상벌점 조회를 실패했습니다.")
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}