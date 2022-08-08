package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.upload.UploadImageState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.isNotEmailValid
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.isNotPhoneNumberValid
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.usecase.member.ChangeMemberInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.upload.UploadImgUseCase
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases,
    private val uploadImgUseCase: UploadImgUseCase
) : BaseViewModel() {

    private val _editProfileState = MutableStateFlow<EditProfileState>(EditProfileState(isLoading = false))
    val editProfileState: StateFlow<EditProfileState> = _editProfileState

    private val _uploadImageState = MutableStateFlow<UploadImageState>(UploadImageState(isLoading = false))
    val uploadImageState: StateFlow<UploadImageState> = _uploadImageState

    lateinit var picture: Picture

    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    var memberId: String = ""
    var type: String = ""

    var file: File? = null

    private val isChangeMemberInfoLoading = MutableLiveData(false)
    private val isUploadImgLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isChangeMemberInfoLoading, isUploadImgLoading)
    }

    private fun saveInfo() {
        memberUseCases.changeMemberInfo(
            ChangeMemberInfo.Params(
                memberId = memberId,
                phone = phone.value ?: "",
                email = email.value ?: "",
                profileImage = picture
            )
        ).divideResult(
            isChangeMemberInfoLoading,
            { _editProfileState.value = EditProfileState(message = it ?: "성공하였습니다.") },
            { _editProfileState.value = EditProfileState(error = it ?: "프로필 수정에 실패하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun uploadImg() {
        uploadImgUseCase(file!!).divideResult(
            isUploadImgLoading,
            {_uploadImageState.value = UploadImageState(picture = it)},
            {_uploadImageState.value = UploadImageState(error = it ?: "이미지 업로드에 실패했습니다.")}
        ).launchIn(viewModelScope)
    }

    fun checkForm() {
        val isEmpty = email.value.isNullOrBlank() || phone.value.isNullOrBlank()
        if (isEmpty) {
            viewEvent(EVENT_EMPTY)
            return
        }

        val isNotMatchForm = phone.value!!.length != 11 || email.value!!.length !in 10..30
        if (isNotMatchForm) {
            viewEvent(EVENT_NOT_MATCH_FORM)
            return
        }

        val isNotPhone = phone.value!!.isNotPhoneNumberValid()
        if (isNotPhone) {
            viewEvent(EVENT_NOT_PHONE_NUMBER)
            return
        }

        val isNotEmail = email.value!!.isNotEmailValid()
        if (isNotEmail) {
            viewEvent(EVENT_NOT_EMAIL)
            return
        }

        saveInfo()
    }

    companion object {
        const val EVENT_EMPTY = 1
        const val EVENT_NOT_MATCH_FORM = 2
        const val EVENT_NOT_PHONE_NUMBER = 3
        const val EVENT_NOT_EMAIL = 4
    }
}