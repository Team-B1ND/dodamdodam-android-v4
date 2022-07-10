package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInState
import kr.hs.dgsw.smartschool.domain.usecase.auth.SignUpUseCase
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SignUpDetailViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {

    var id: String = ""
    var pw: String = ""

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState(isLoading = false))
    val signUpState: StateFlow<SignUpState> = _signUpState

    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val room = MutableLiveData<String>()
    val number = MutableLiveData<String>()

    fun signUp() {
        signUpUseCase(
            SignUpUseCase.Params(
                id = id,
                pw = pw,
                email = email.value ?: "",
                phone = phone.value ?: "",
                name = name.value ?: "",
                grade = grade.value ?: "0",
                classroom = room.value ?: "0",
                number = number.value ?: "0"
            )
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _signUpState.value = SignUpState(result = result.data ?: "")
                }
                is Resource.Loading -> {
                    _signUpState.value = SignUpState(isLoading = true)
                }
                is Resource.Error -> {
                    _signUpState.value = SignUpState(
                        error = result.message ?: "회원가입에 실패했습니다."
                    )
                }
            }
        }
    }
}