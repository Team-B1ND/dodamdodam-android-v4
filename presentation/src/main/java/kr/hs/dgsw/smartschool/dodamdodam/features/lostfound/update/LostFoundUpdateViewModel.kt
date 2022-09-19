package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LostFoundUpdateViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    val isLost = MutableLiveData<Boolean>(true)
    val isFound = MutableLiveData<Boolean>()
    val title = MutableLiveData<String>()
    val place = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    var file : File? = null
    var picture : Picture? = null

    private val isGetLostFoundLoading = MutableLiveData<Boolean>()
    private val isModifyLostFoundLoading = MutableLiveData<Boolean>()

    init{
        combineLoadingVariable(isGetLostFoundLoading,isModifyLostFoundLoading)

    }
    companion object{
        const val EVENT_SUCCESS = 1
        const val EVENT_EMPTY_TITLE = 2
        const val EVENT_EMPTY_CONTENT = 3
        const val EVENT_ERROR = 4
    }
    fun getLostFound(id:Int){
        
    }
    fun modifyLostFound(){
        if(title.value.isNullOrEmpty()) viewEvent(EVENT_EMPTY_TITLE)
        if(content.value.isNullOrEmpty()) viewEvent(EVENT_EMPTY_CONTENT)
        useCases.addLostFound(LostFoundDataRequest(
            content = content.value ?: "",
            picture = file!!.path,
            place = place.value ?: "",
            title = title.value ?: "",
            type = if (isLost.value == true) "LOST" else "FOUND",
            lostFoundId = null
        )).divideResult(
            isModifyLostFoundLoading,
            {viewEvent(EVENT_SUCCESS)},
            {viewEvent(EVENT_ERROR)}
        ).launchIn(viewModelScope)
    }

}
