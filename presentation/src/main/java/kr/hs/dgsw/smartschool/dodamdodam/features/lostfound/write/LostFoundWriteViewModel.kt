package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.write

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.usecase.lostfound.LostFoundUseCases
import kr.hs.dgsw.smartschool.domain.usecase.upload.UploadImgUseCase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class LostFoundWriteViewModel @Inject constructor(
    private val useCases: LostFoundUseCases,
    private val imageUseCase: UploadImgUseCase
) : BaseViewModel() {
    val isLost = MutableLiveData<Boolean>(true)
    val isFound = MutableLiveData<Boolean>(false)
    val title = MutableLiveData<String>()
    val place = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    var file: File? = null
    var url: String? = null

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
    }

    fun addLostFound() {
        imageUpload(file ?: File("res/drawable/default_img.png"))
        if (title.value.isNullOrEmpty()) viewEvent(EVENT_EMPTY_TITLE)
        if (content.value.isNullOrEmpty()) viewEvent(EVENT_EMPTY_CONTENT)
        useCases.addLostFound(
            LostFoundDataRequest(
                content = content.value ?: "",
                picture = url ?: "",
                place = place.value ?: "위치 정보 없음",
                title = title.value ?: "",
                type = if (isLost.value == true) "LOST" else "FOUND",
                lostFoundId = null
            )
        ).divideResult(
            isModifyLostFoundLoading,
            { viewEvent(EVENT_SUCCESS) },
            { viewEvent(EVENT_ERROR) }
        ).launchIn(viewModelScope)
    }
    private fun imageUpload(file: File) {
        imageUseCase(file).divideResult(
            isModifyLostFoundLoading,
            { url = it?.url!! },
            {}
        )
    }
}
