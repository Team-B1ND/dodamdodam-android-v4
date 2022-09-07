package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.DeleteLostFound
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.DeleteLostFoundComment
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.GetLostFoundComment
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import javax.inject.Inject

@HiltViewModel
class LostFoundCommentViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    private val _getCommentState = MutableSharedFlow<GetCommentState>()
    private val isGetCommentLoading = MutableLiveData<Boolean>()

    val getCommentState = _getCommentState
    fun getComment(idx : Int){
        useCases.getLostFoundComment(GetLostFoundComment.Params(lostFoundIdx = idx)).divideResult(
            isGetCommentLoading,
            {viewModelScope.launch { GetCommentState(list = it ?: emptyList())}},
            {viewModelScope.launch { GetCommentState(error = "댓글을 불러오는 데에 실패하였습니다.")}}
        ).launchIn(viewModelScope)
    }
    fun addComment(comment : String, idx : Int){
        useCases.addLostFoundComment(AddCommentRequest(comment = comment, lostFoundId = idx)).divideResult(
            isGetCommentLoading,
            {viewModelScope.launch { GetCommentState()}},
            {viewModelScope.launch { GetCommentState(error = "댓글을 추가하는 데에 실패하였습니다.")}}
        ).launchIn(viewModelScope)
    }
    fun modifyComment(comment : String, idx : Int){
        useCases.modifyLostFoundComment(ModifyCommentRequest(comment = comment, commentId = idx)).divideResult(
            isGetCommentLoading,
            {viewModelScope.launch { GetCommentState()}},
            {viewModelScope.launch { GetCommentState(error = "댓글을 수정하는 데에 실패하였습니다.")}}
        ).launchIn(viewModelScope)
    }
    fun deleteComment(idx : Int){
        useCases.deleteLostFoundComment(DeleteLostFoundComment.Params(commentIdx = idx)).divideResult(
            isGetCommentLoading,
            {viewModelScope.launch { GetCommentState()}},
            {viewModelScope.launch { GetCommentState(error = "댓글을 삭제하는 데에 실패하였습니다.")}}
        ).launchIn(viewModelScope)
    }
}
