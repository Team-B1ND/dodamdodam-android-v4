package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFoundComment
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import javax.inject.Inject

@HiltViewModel
class LostFoundCommentViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    private val isGetCommentLoading = MutableLiveData<Boolean>()
    fun getComment(idx : Int){
        useCases.getLostFoundComment(GetLostFoundComment.Params(lostFoundIdx = idx)).divideResult(
            isGetCommentLoading,
            {},
            {}
        ).launchIn(viewModelScope)
    }
    fun addComment(){
        useCases.addLostFoundComment().divideResult(
            isGetCommentLoading,
            {},
            {}
        ).launchIn(viewModelScope)
    }
    fun modifyComment(){
        useCases.modifyLostFoundComment().divideResult(
            isGetCommentLoading,
            {},
            {}
        ).launchIn(viewModelScope)
    }
    fun deleteComment(){
        useCases.deleteLostFoundComment().divideResult(
            isGetCommentLoading,
            {},
            {}
        ).launchIn(viewModelScope)
    }
}
