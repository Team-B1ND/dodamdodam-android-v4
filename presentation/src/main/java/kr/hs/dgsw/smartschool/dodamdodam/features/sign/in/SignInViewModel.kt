package kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.auth.SignInUseCase
import kr.hs.dgsw.smartschool.domain.usecase.token.TokenUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val tokenUseCases: TokenUseCases
) : BaseViewModel() {

    companion object {
        const val EVENT_SUCCESS_SIGN_IN = 1234
    }

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    private val _signInState = MutableStateFlow<SignInState>(SignInState(isLoading = false))
    val signInState: StateFlow<SignInState> = _signInState

    fun onClickSignIn() {
        Log.d("TestTest", "onClickSignIn: ${id.value} ${pw.value}")
        if (id.value.isNullOrBlank() || pw.value.isNullOrBlank()) {
            Log.d("TestTest", "onClickSignIn: 실행")
            onErrorEvent.value = Throwable("아이디와 패스워드를 입력해 주세요")
            return
        }
        Log.d("TestTest", "onClickSignIn")
        signIn()
    }

    private fun signIn() {
        signInUseCase(
            id = id.value ?: "",
            pw = pw.value ?: ""
        ).onEach { result ->
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
        }.launchIn(viewModelScope)
    }
}