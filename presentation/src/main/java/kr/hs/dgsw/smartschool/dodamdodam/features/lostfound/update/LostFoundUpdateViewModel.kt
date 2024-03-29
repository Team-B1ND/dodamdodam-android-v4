package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.update

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.ModifyLostFound
import kr.hs.dgsw.smartschool.domain.usecase.upload.UploadFileUseCase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LostFoundUpdateViewModel @Inject constructor(
    private val useCases: LostFoundUseCases,
    private val uploadFileUseCase: UploadFileUseCase
) : BaseViewModel() {
    val isLost = MutableLiveData<Boolean>(true)
    val isFound = MutableLiveData<Boolean>(false)
    val title = MutableLiveData<String>()
    val place = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    var url: String? = null
    val callBack = LostFoundUpdateFragment()

    private val isGetLostFoundLoading = MutableLiveData<Boolean>()
    private val isModifyLostFoundLoading = MutableLiveData<Boolean>()

    init {
        combineLoadingVariable(isGetLostFoundLoading, isModifyLostFoundLoading)
    }
    companion object {
        const val EVENT_SUCCESS = 1
        const val EVENT_EMPTY_TITLE = 2
        const val EVENT_EMPTY_CONTENT = 3
        const val EVENT_ERROR = 4
        const val EVENT_LOAD_IMG = 5
    }
    fun getLostFound(id: Int) {
        useCases.getLostFoundById(id).divideResult(
            isModifyLostFoundLoading,
            {
                url = it?.image ?: ""
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

                Log.e("LostFoundUpdateViewModel", "현재 url $url")
                viewEvent(EVENT_LOAD_IMG)
            },
            { }
        ).launchIn(viewModelScope)
    }
    fun imageUpload(file: File) {
        Log.e("LostFoundWriteViewModel", "imageUpload()")
        uploadFileUseCase(file).divideResult(
            isModifyLostFoundLoading,
            {
                url = it
                Log.e("LostFoundWriteViewModel", "imageUpload $url")
            },
            {}
        ).launchIn(viewModelScope)
    }
    fun modifyLostFound(id: Int) {
        if (title.value.isNullOrEmpty()) viewEvent(EVENT_EMPTY_TITLE)
        if (content.value.isNullOrEmpty()) viewEvent(EVENT_EMPTY_CONTENT)
        useCases.modifyLostFound(
            ModifyLostFound.Param(
                content = content.value ?: "".replace(" ", ""),
                picture = url ?: "",
                place = place.value ?: "".replace(" ", ""),
                title = title.value ?: "".replace(" ", ""),
                type = if (isLost.value == true) "LOST" else "FOUND",
                lostFoundId = id
            )
        ).divideResult(
            isModifyLostFoundLoading,
            { viewEvent(EVENT_SUCCESS) },
            { viewEvent(EVENT_ERROR) }
        ).launchIn(viewModelScope)
    }
}
