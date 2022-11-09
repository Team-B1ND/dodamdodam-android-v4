package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state.GetLostFoundState
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state.GetMyInfoState
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.DeleteLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import javax.inject.Inject

@HiltViewModel
class LostFoundViewModel @Inject constructor(
    private val useCases: LostFoundUseCases,
    private val memberUseCases: MemberUseCases
) : BaseViewModel() {

    private val _getLostFoundState = MutableSharedFlow<GetLostFoundState>()
    val getLostFoundState: SharedFlow<GetLostFoundState> = _getLostFoundState
    private val _getInfoState = MutableSharedFlow<GetMyInfoState>()
    val getMyInfoState: SharedFlow<GetMyInfoState> = _getInfoState

    private val isGetLostFoundLoading = MutableLiveData<Boolean>()
    private val isGetLostProfileLoading = MutableLiveData<Boolean>()

    private val searchKeyword = MutableLiveData<String>()
    val mineChecked = MutableLiveData<Boolean>(false)
    val foundChecked = MutableLiveData<Boolean>(false)

    var hasLostFound = false

    init {
        combineLoadingVariable(isGetLostFoundLoading, isGetLostProfileLoading)
    }

    fun getMyInfo() {
        memberUseCases.getMyInfo(Unit).divideResult(
            isGetLostProfileLoading,
            { viewModelScope.launch { _getInfoState.emit(GetMyInfoState(myId = it?.member?.id ?: "")) } },
            { viewModelScope.launch { _getInfoState.emit(GetMyInfoState(error = "내 정보를 불러오는 데에 실패하였습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun getLostFoundList() {
        if (hasLostFound) {
            if (mineChecked.value == true)
                myLostFound()
            else {
                useCases.getLostFoundAll(Unit).divideResult(
                    isGetLostFoundLoading,
                    { launchLostFound(it!!) },
                    { launchLostFound("분실 게시물을 불러오는 데에 실패하였습니다.") }
                ).launchIn(viewModelScope)
            }
        }
    }

    private fun myLostFound() {
        useCases.getMyLostFound(Unit).divideResult(
            isGetLostFoundLoading,
            { launchLostFound(it!!) },
            { launchLostFound("분실 게시물을 불러오는 데에 실패하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun deleteLostFound(idx: Int) {
        useCases.deleteLostFound(DeleteLostFound.Params(idx = idx)).divideResult(
            isGetLostFoundLoading,
            { getLostFoundList() },
            { launchLostFound("분실 게시물을 삭제하는 데에 실패하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun launchLostFound(list: List<LostFound>) {
        val newList = mutableListOf<LostFound>()

        list.forEach {
            if (foundChecked.value == true && it.type == "FOUND") newList.add(it)
            else if (foundChecked.value == false && it.type == "LOST") newList.add(it)
        }

        viewModelScope.launch { _getLostFoundState.emit(GetLostFoundState(list = newList)) }
    }

    private fun launchLostFound(error: String) {
        viewModelScope.launch { _getLostFoundState.emit(GetLostFoundState(error = error)) }
    }
}
