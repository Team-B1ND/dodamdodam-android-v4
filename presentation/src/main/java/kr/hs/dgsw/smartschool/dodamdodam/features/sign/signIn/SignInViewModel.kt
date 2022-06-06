package kr.hs.dgsw.smartschool.dodamschool.ui.features.sign.signin

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamschool.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.request.SignInRequest
import kr.hs.dgsw.smartschool.domain.usecase.signin.SignInUseCase
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel(){
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    private val _state = MutableStateFlow<SignInState>(SignInState(isLoading = false))
    val state: StateFlow<SignInState> = _state

    companion object {
        const val EVENT_ON_CLICK_SIGN_UP = 123123
    }

    fun onClickSignIn(){
        if(checkError()){
            val signInRequest =
                SignInRequest(
                    id.value.toString(),
                    getHash(pw.value.toString())
                )
            signInUseCase(signInRequest).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        Log.e("로그인 결과", "완료 ${result.data}")
                        _state.value = SignInState(data = result.data)
                    }
                    is Resource.Loading -> {
                        Log.e("로그인 결과", "로딩 ${result.data}")
                        _state.value = SignInState(isLoading = true)
                    }
                    is Resource.Error -> {
                        Log.e("로그인 결과", "오류 ${result.data}")
                        _state.value = SignInState(error = result.message ?: "오류가 발생했습니다.")
                    }

                }
            }.launchIn(viewModelScope)
            Log.e("SignInViewModel","${signInRequest.id} ${signInRequest.pw}")
        }
    }
    fun setError(Id : MutableLiveData<String>) : String? {
        when (Id) {
            id -> {
                if(!Pattern.matches("^([a-zA-Z0-9])+$", Id.value ?: "")) {
                    return "영문과 숫자만 사용할 수 있습니다"
                }
                else if(Id.value?.length!! < 6 ) {
                       return "6~15자를 입력해주십시오"
                }
                else if (Id.value == null) {
                       return "아이디를 입력해주십시오"
                }
                else {
                        return null
                }
            }
            pw -> {
                if (Id.value?.length!! < 8) {
                    return "8~15자를 입력해주십시오"
                } else if (!Pattern.matches(
                        "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$",
                        Id.value ?: ""
                    )
                ) {
                    return "잘못된 입력방식입니다"
                } else if (Id.value == null) {
                    return "비밀번호를 입력해주십시오"
                } else {
                    return null
                }

            }
        }
        return null
    }

    fun checkError():Boolean{
        if(!Pattern.matches("^([a-zA-Z0-9])+$", id.value ?: "") || id.value?.length!! < 6 || id.value == null) {
            return false
        }
        if(!Pattern.matches("^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$", pw.value ?: "") ||pw.value?.length!! < 8 || pw.value == null) {
            return false
        }
        return true
    }

    fun toSignUp(){
        viewEvent(EVENT_ON_CLICK_SIGN_UP)
    }
    fun getHash(str: String): String {
        var digest: String = ""
        digest = try {

            //암호화
            val sh = MessageDigest.getInstance("SHA-512") // SHA-256 해시함수를 사용
            sh.update(str.toByteArray()) // str의 문자열을 해싱하여 sh에 저장
            val byteData = sh.digest() // sh 객체의 다이제스트를 얻는다.


            //얻은 결과를 hex string으로 변환
            val hexChars = "0123456789ABCDEF"
            val hex = CharArray(byteData.size * 2)
            for (i in byteData.indices) {
                val v = byteData[i].toInt() and 0xff
                hex[i * 2] = hexChars[v shr 4]
                hex[i * 2 + 1] = hexChars[v and 0xf]
            }

            String(hex) //최종 결과를 String 으로 변환

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            "" //오류 뜰경우 stirng은 blank값임
        }
        return digest
    }
}