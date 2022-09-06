package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import javax.inject.Inject

@HiltViewModel
class LostFoundCommentViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    fun getComment(){

    }
    fun addComment(){

    }
    fun modifyComment(){

    }
    fun deleteComment(){

    }
}
