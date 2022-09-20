package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.state.GetLostFoundState
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFoundData
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LostFoundUpdateViewModel @Inject constructor(
    private val useCases : LostFoundUseCases
) : BaseViewModel(){
    val isLost = MutableLiveData<Boolean>(true)
    val isFound = MutableLiveData<Boolean>(false)
    val title = MutableLiveData<String>()
    val place = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    var file : File? = null
    val callBack = LostFoundUpdateFragment()

    private val isGetLostFoundLoading = MutableLiveData<Boolean>()
    private val isModifyLostFoundLoading = MutableLiveData<Boolean>()

    interface ImageCallBack{
        fun updateImage(image : String)
    }

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
        useCases.getLostFoundById(id).divideResult(
            isModifyLostFoundLoading,
            {   callBack.updateImage(it?.image ?: "")
                title.value = it?.title
                if (it?.type!!.equals("FOUND")) {
                    isFound.value = true
                    isLost.value = false
                } else {
                    isLost.value = true
                    isFound.value = false
                }
                place.value = it.place
                content.value = it.content
            },
            {viewModelScope.launch { it}}
        ).launchIn(viewModelScope)
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
