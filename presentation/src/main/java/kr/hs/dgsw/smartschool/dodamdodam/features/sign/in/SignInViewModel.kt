package kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.removeBlankInString
import kr.hs.dgsw.smartschool.domain.usecase.auth.SignInUseCase
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    companion object {
        const val EVENT_SUCCESS_SIGN_IN = 1234
    }

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    private val _signInState = MutableStateFlow(SignInState(isLoading = false))
    val signInState: StateFlow<SignInState> = _signInState

    fun onClickSignIn() {
        if (id.value.isNullOrBlank() || pw.value.isNullOrBlank()) {
            onErrorEvent.value = Throwable("아이디와 패스워드를 입력해 주세요")
            return
        }
        signIn()
    }

    private fun signIn() {
        signInUseCase(
            id = id.value?.removeBlankInString() ?: "",
            pw = pw.value?.removeBlankInString() ?: ""
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    viewEvent(EVENT_SUCCESS_SIGN_IN)
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    _signInState.value = SignInState(isLoading = true)
                    isLoading.value = true
                }
                is Resource.Error -> {
                    _signInState.value = SignInState(
                        error = result.message ?: "로그인에 실패하였습니다."
                    )
                    isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }
}