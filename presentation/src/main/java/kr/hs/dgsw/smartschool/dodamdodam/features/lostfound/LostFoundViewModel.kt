package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFoundUseCase
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import javax.inject.Inject

@HiltViewModel
class LostFoundViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    private val _getLostFoundState = MutableSharedFlow<GetLostFoundState>()
    val getLostFoundState : SharedFlow<GetLostFoundState> = _getLostFoundState
    private val isGetLostFoundLoading = MutableLiveData<Boolean>()

    init {
        getLostFoundList(1,0)
    }
    fun getLostFoundList(page : Int, type : Int){
        useCases.getLostFound(GetLostFoundUseCase.Params(page = page, type = type)).divideResult(
            isGetLostFoundLoading,
            {viewModelScope.launch {   GetLostFoundState(list = it ?: emptyList())}},
            {viewModelScope.launch { GetLostFoundState(error = "분실 게시물을 불러오는데 실패하였습니다.")}}
        ).launchIn(viewModelScope)
    }
}

