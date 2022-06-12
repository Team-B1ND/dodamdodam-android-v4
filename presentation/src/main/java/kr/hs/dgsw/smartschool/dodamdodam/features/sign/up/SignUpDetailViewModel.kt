package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInState
import kr.hs.dgsw.smartschool.domain.usecase.auth.SignInUseCase
import kr.hs.dgsw.smartschool.domain.usecase.auth.SignUpUseCase
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SignUpDetailViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    companion object {
        const val EVENT_SUCCESS_SIGN_IN = 11
    }

    val id: String = ""
    val pw: String = ""

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState(isLoading = false))
    val signUpState: StateFlow<SignUpState> = _signUpState

    private val _signInState = MutableStateFlow<SignInState>(SignInState(isLoading = false))
    val signInState: StateFlow<SignInState> = _signInState

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> get() = _phone

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name

    private val _grade = MutableLiveData<String>()
    val grade: LiveData<String> get() = _grade

    private val _room = MutableLiveData<String>()
    val room: LiveData<String> get() = _room

    private val _number = MutableLiveData<String>()
    val number: LiveData<String> get() = _number

    fun signUp() {
        val successId = id
        val successPw = pw
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
                    signIn(successId, successPw)
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

    private fun signIn(id: String, pw: String) {
        signInUseCase(id, pw).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    viewEvent(EVENT_SUCCESS_SIGN_IN)
                }
                is Resource.Loading -> {
                    _signInState.value = SignInState(isLoading = true)
                }
                is Resource.Error -> {
                    _signInState.value = SignInState(
                        error = result.message ?: "로그인에 실패했습니다."
                    )
                }
            }
        }
    }
}