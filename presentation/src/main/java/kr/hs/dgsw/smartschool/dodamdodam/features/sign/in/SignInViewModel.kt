package kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.removeBlankInString
import kr.hs.dgsw.smartschool.domain.usecase.auth.SignInUseCase
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
            return
        }
        signIn()
    }

    private fun signIn() {
        signInUseCase(
            SignInUseCase.Params(
                id = id.value?.removeBlankInString() ?: "",
                pw = pw.value?.removeBlankInString() ?: ""
            )
        ).divideResult(
            isLoading,
            { viewEvent(EVENT_SUCCESS_SIGN_IN) },
            { _signInState.value = SignInState(error = it ?: "로그인에 실패하였습니다.") }
        ).launchIn(viewModelScope)
    }
}
