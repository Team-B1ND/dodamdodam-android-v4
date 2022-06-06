package kr.hs.dgsw.smartschool.dodamschool.ui.features.sign.signin

import android.service.autofill.UserData
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.model.response.SignIn
import kr.hs.dgsw.smartschool.domain.usecase.signin.AutoSignInUseCase
import kr.hs.dgsw.smartschool.domain.usecase.signin.SignInUseCase
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class AutoSignInViewModel @Inject constructor(
    private val autoSignInUseCase: AutoSignInUseCase
    ) : BaseViewModel() {

    private val _state = MutableStateFlow<AutoSignInState>(AutoSignInState(isLoading = false))
    val state: StateFlow<AutoSignInState> = _state
        fun autoSignIn() : SignIn? {
            var signInResult : SignIn? = null
            autoSignInUseCase().onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        Log.e("자동 로그인 결과", "완료 ${result.data?.token}")
                        _state.value = AutoSignInState(data = result.data)
                        signInResult = result.data
                    }
                    is Resource.Loading -> {
                        Log.e("자동 로그인 결과", "로딩 ${result.data}")
                        _state.value = AutoSignInState(isLoading = true)
                    }
                    is Resource.Error -> {
                        Log.e("자동 로그인 결과", "오류 ${result.data}")
                        _state.value = AutoSignInState(error = result.message ?: "오류가 발생했습니다.")
                    }

                }
            }.launchIn(viewModelScope)
            Log.e("SignInViewModel","")
            return signInResult
        }
}