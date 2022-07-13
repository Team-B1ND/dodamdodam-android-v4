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
import kr.hs.dgsw.smartschool.domain.model.lostfound.Picture
import kr.hs.dgsw.smartschool.domain.usecase.member.ChangeMemberInfo
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases
) : BaseViewModel() {

    private val _editProfileState = MutableStateFlow<EditProfileState>(EditProfileState(isLoading = false))
    val editProfileState: StateFlow<EditProfileState> = _editProfileState

    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    var memberId: String = ""
    var type: String = ""
    val profileOriginalName = MutableLiveData<String>()
    val profileUploadName = MutableLiveData<String>()


    // TODO 뒤로가기 누를 시에 전 화면으로 펑
    // TODO 이미지 가공

    fun saveInfo() {
        Log.d("TestTest", "saveInfo: ${memberId} ${email.value}")
        memberUseCases.changeMemberInfo(
            ChangeMemberInfo.Params(
                memberId = memberId,
                phone = phone.value ?: "",
                email = email.value ?: "",
                profileImage = Picture(
                    originalName = profileOriginalName.value ?: "",
                    uploadName = profileUploadName.value ?: "",
                    type = type
                )
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


}