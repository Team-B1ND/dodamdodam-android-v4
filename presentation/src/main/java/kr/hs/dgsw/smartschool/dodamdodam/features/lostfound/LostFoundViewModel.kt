package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFoundUseCase
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import javax.inject.Inject

class LostFoundViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    private val _getLostFoundState = MutableStateFlow<GetLostFoundState>(GetLostFoundState(isLoading = false))
    val getLostFoundState = _getLostFoundState
    private val isGetLostFoundLoading = MutableLiveData<Boolean>()
    fun getLostFoundList(page : Int, type : Int){
        useCases.getLostFound(GetLostFoundUseCase.Params(page = page, type = type)).divideResult(
            isGetLostFoundLoading,
            {_getLostFoundState.value = GetLostFoundState(list = it ?: emptyList())},
            {_getLostFoundState.value = GetLostFoundState(error = "분실 게시물을 불러오는데 실패하였습니다.")}
        )
    }
}

