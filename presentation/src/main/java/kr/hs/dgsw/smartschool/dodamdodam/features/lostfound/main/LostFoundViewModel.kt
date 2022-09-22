package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state.GetLostFoundState
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.DeleteLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.SearchLostFound
import kr.hs.dgsw.smartschool.domain.usecase.member.GetMyInfo
import javax.inject.Inject

@HiltViewModel
class LostFoundViewModel @Inject constructor(
    private val useCases : LostFoundUseCases,
    private val getInfo : GetMyInfo
) : BaseViewModel(){
    private val _getLostFoundState = MutableSharedFlow<GetLostFoundState>()
    val getLostFoundState : SharedFlow<GetLostFoundState> = _getLostFoundState
    private val isGetLostFoundLoading = MutableLiveData<Boolean>()

    val memberInfo = MutableLiveData<Member>()
    val title = MutableLiveData<String>()
    val isChecked = MutableLiveData<Boolean>()
    val selectedItemPosition = MutableLiveData<Int>()

    init {
        combineLoadingVariable(isGetLostFoundLoading)
        Log.e("LostFoundViewModel","생성")
        getMyInfo()
    }
    private fun getMyInfo(){
        getInfo(Unit).divideResult(
            MutableLiveData<Boolean>(),
            {
                memberInfo.value = it?.member
            },
            {}
        )
    }
    fun getLostFoundList(page : Int){
        Log.d("LostFoundViewModel","getLostFoundList()")
        if(isChecked.value == true){
            myLostFound()
        } else{
            useCases.getLostFound(GetLostFound.Params(page = page, type = if(selectedItemPosition.value == 1) "FOUND" else "LOST")).divideResult(
                isGetLostFoundLoading,
                {viewModelScope.launch { _getLostFoundState.emit( GetLostFoundState(list = it ?: emptyList())) }},
                {viewModelScope.launch { _getLostFoundState.emit( GetLostFoundState(error = "분실 게시물을 불러오는 데에 실패하였습니다.")) }}
            ).launchIn(viewModelScope)
        }
    }
    fun searchLostFound(){
        useCases.searchLostFound(SearchLostFound.Params(search = title.value ?: "")).divideResult(
            isGetLostFoundLoading,
            {viewModelScope.launch { GetLostFoundState(list = it ?: emptyList()) }},
            {viewModelScope.launch { GetLostFoundState(error = "분실 게시물을 불러오는 데에 실패하였습니다.") }}
        ).launchIn(viewModelScope)
    }
    private fun myLostFound(){
        useCases.getMyLostFound(Unit).divideResult(
            isGetLostFoundLoading,
            {viewModelScope.launch { GetLostFoundState(list = it ?: emptyList()) }},
            {viewModelScope.launch { GetLostFoundState(error = "분실 게시물을 불러오는 데에 실패하였습니다.") }}
        ).launchIn(viewModelScope)
    }
    fun deleteLostFound(idx : Int){
        useCases.deleteLostFound(DeleteLostFound.Params(idx = idx)).divideResult(
            isGetLostFoundLoading,
            {viewModelScope.launch { GetLostFoundState() }},
            {viewModelScope.launch { GetLostFoundState(error = "분실 게시물을 삭제하는 데에 실패하였습니다.") }}
        ).launchIn(viewModelScope)
    }
}

