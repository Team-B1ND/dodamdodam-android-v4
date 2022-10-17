package kr.hs.dgsw.smartschool.dodamdodam.features.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.state.LoginState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.removeBlankInString
import kr.hs.dgsw.smartschool.domain.usecase.auth.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState: SharedFlow<LoginState> = _loginState

    fun onClickSignIn() {
        if (id.value.isNullOrBlank() || pw.value.isNullOrBlank()) {
            return
        }
        login()
    }

    private fun login() {
        loginUseCase(
            LoginUseCase.Params(
                id = id.value?.removeBlankInString() ?: "",
                pw = pw.value?.removeBlankInString() ?: ""
            )
        ).divideResult(
            isLoading,
            { viewModelScope.launch { _loginState.emit(LoginState(isSuccess = true)) } },
            { viewModelScope.launch { _loginState.emit(LoginState(error = it ?: "로그인에 실패하였습니다.")) } }
        ).launchIn(viewModelScope)
    }
}
