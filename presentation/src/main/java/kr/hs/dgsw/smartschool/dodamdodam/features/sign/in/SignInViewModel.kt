package kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`

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
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    companion object {
        const val EVENT_SUCCESS_LOGIN = 1234
    }

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    private val _pw = MutableLiveData<String>()
    val pw: LiveData<String> get() = _pw

    private val _signInState = MutableStateFlow<SignInState>(SignInState(isLoading = false))
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
            id = id.value ?: "",
            pw = pw.value ?: ""
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    viewEvent(EVENT_SUCCESS_LOGIN)
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