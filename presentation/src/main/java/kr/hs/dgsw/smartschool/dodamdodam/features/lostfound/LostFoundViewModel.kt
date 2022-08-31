package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFoundUseCase
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import javax.inject.Inject

@HiltViewModel
class LostFoundViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    private val _getLostFoundState = MutableStateFlow<GetLostFoundState>(GetLostFoundState(isLoading = false))
    val getLostFoundState : StateFlow<GetLostFoundState> = _getLostFoundState
    private val isGetLostFoundLoading = MutableLiveData<Boolean>()

    init {
        getLostFoundList(1,0)
    }
    fun getLostFoundList(page : Int, type : Int){
        useCases.getLostFound(GetLostFoundUseCase.Params(page = page, type = type)).divideResult(
            isGetLostFoundLoading,
            {_getLostFoundState.value = GetLostFoundState(list = it ?: emptyList())
                Log.d("LostFoundViewModel",_getLostFoundState.value.list.toString())},
            {_getLostFoundState.value = GetLostFoundState(error = "분실 게시물을 불러오는데 실패하였습니다.")
            Log.d("LostFoundViewModel",_getLostFoundState.value.error)}
        ).launchIn(viewModelScope)
    }
}

