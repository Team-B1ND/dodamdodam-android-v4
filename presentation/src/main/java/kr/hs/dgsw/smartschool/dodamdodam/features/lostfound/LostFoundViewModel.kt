package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.DeleteLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.SearchLostFound
import javax.inject.Inject

@HiltViewModel
class LostFoundViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    private val _getLostFoundState = MutableSharedFlow<GetLostFoundState>()
    val getLostFoundState : SharedFlow<GetLostFoundState> = _getLostFoundState
    private val isGetLostFoundLoading = MutableLiveData<Boolean>()

    init {
        getLostFoundList(1,"LOST")
    }
    fun getLostFoundList(page : Int, type : String){
        useCases.getLostFound(GetLostFound.Params(page = page, type = type)).divideResult(
            isGetLostFoundLoading,
            {viewModelScope.launch {   GetLostFoundState(list = it ?: emptyList())}},
            {viewModelScope.launch { GetLostFoundState(error = "분실 게시물을 불러오는 데에 실패하였습니다.")}}
        ).launchIn(viewModelScope)
    }

    fun searchLostFound(title :String){
        useCases.searchLostFoundUseCase(SearchLostFound.Params(search = title)).divideResult(
            isGetLostFoundLoading,
            {viewModelScope.launch { GetLostFoundState(list = it ?: emptyList()) }},
            {viewModelScope.launch { GetLostFoundState(error = "분실 게시물을 불러오는 데에 실패하였습니다.") }}
        ).launchIn(viewModelScope)
    }
    fun myLostFound(){
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
            {viewModelScope.launch { GetLostFoundState(error = "분실 게시물을 불러오는 데에 실패하였습니다.") }}
        ).launchIn(viewModelScope)
    }
}

