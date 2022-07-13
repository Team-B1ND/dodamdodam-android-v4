package kr.hs.dgsw.smartschool.dodamdodam.features.profile.edit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.upload.UploadImageState
import kr.hs.dgsw.smartschool.domain.model.fileupload.Picture
import kr.hs.dgsw.smartschool.domain.usecase.member.ChangeMemberInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.upload.UploadImgUseCase
import kr.hs.dgsw.smartschool.domain.util.Resource
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

    fun saveInfo() {
        memberUseCases.changeMemberInfo(
            ChangeMemberInfo.Params(
                memberId = memberId,
                phone = phone.value ?: "",
                email = email.value ?: "",
                profileImage = picture
            )
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _editProfileState.value = EditProfileState(message = result.data ?: "성공하였습니다.")
                }
                is Resource.Loading -> {
                    _editProfileState.value = EditProfileState(isLoading = true)
                }
                is Resource.Error -> {
                    _editProfileState.value = EditProfileState(
                        error = result.message ?: "프로필 수정에 실패하였습니다."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun uploadImg() {
        uploadImgUseCase(file!!).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _uploadImageState.value = UploadImageState(picture = result.data)
                }
                is Resource.Loading -> {
                    _uploadImageState.value = UploadImageState(isLoading = true)
                }
                is Resource.Error -> {
                    _uploadImageState.value = UploadImageState(error = result.message ?: "이미지 업로드에 실패했습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }
}