package kr.hs.dgsw.smartschool.dodamdodam.features.sign.signUp

import android.content.Intent
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.SignUpUseCase
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.regex.Pattern
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val generation = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val classNum = MutableLiveData<String>()
    val stuNum = MutableLiveData<String>()

    private val _state = MutableStateFlow<SignUpState>(SignUpState(isLoading = false))
    val state: StateFlow<SignUpState> = _state

    companion object {
        const val EVENT_ON_ERROR_ID = 44444
        const val EVENT_ON_ERROR_EMAIL = 33333
        const val EVENT_ON_ERROR_PW = 22222
    }


    /*fun onClickSignUp() {
        if (checkError()) {
            val signUpRequest = SignUpRequest(
                id = id.value ?: "",
                pw = getHash(password.value!!),
                generation = generation.value?.toInt() ?: 0,
                email = email.value ?: "",
                name = name.value ?: "",
                phone = phone.value ?: "",
                grade = grade.value?.toInt() ?: -1,
                classNum = classNum.value?.toInt() ?: -1,
                stuNum = stuNum.value?.toInt() ?: -1,
                role = "student"
            )

            signUpUseCase(signUpRequest).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = SignUpState(result = result.data ?: "회원가입에 성공했습니다.")
                    }
                    is Resource.Loading -> {
                        _state.value = SignUpState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _state.value = SignUpState(error = "회원가입에 실패했습니다.")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }*/


    private fun checkError(): Boolean {
        // 에딧텍스트 포커스 옮기는 작업 해야함, EVENT 써야할듯
        if (!Pattern.matches("^([a-zA-Z0-9])+$", id.value ?: "") || id.value?.length!! < 6 || id.value == null) {
            onErrorEventId()
            return false
        } else if (!Pattern.matches("^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$", password.value ?: "") || password.value?.length!! < 8 || password.value == null) {
            onErrorEventPw()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.value ?: "").matches() || email.value == null) {
            onErrorEventEmail()
            return false
        }
        return true
    }

    fun setError(value: MutableLiveData<String>): String? {
        when (value) {
            id -> {
                return if (!Pattern.matches("^([a-zA-Z0-9])+$", value.value ?: "")) {
                    "영문과 숫자만 사용할 수 있습니다"
                } else if (value.value?.length!! < 6) {
                    "6~15자를 입력해주십시오"
                } else if (value.value == null) {
                    "아이디를 입력해주십시오"
                } else {
                    null
                }
            }
            password -> {
                return if (value.value?.length!! < 8) {
                    "8~15자를 입력해주십시오"
                } else if (!Pattern.matches(
                        "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$",
                        value.value ?: ""
                    )
                ) {
                    "잘못된 입력방식입니다"
                } else if (value.value == null) {
                    "비밀번호를 입력해주십시오"
                } else {
                    null
                }

            }
            email -> {
                return if (!Patterns.EMAIL_ADDRESS.matcher(value.value ?: "").matches()) {
                    "이메일 형식이 잘못되었습니다"
                } else if (value.value == null) {
                    "이메일을 입력해주십시오"
                } else {
                    null
                }

            }
        }
        return null
    }

    fun toLogin() {
        val intent: Intent
    }


    private fun getHash(str: String): String {
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

    private fun onErrorEventId() = viewEvent(EVENT_ON_ERROR_ID)
    private fun onErrorEventPw() = viewEvent(EVENT_ON_ERROR_PW)
    private fun onErrorEventEmail() = viewEvent(EVENT_ON_ERROR_EMAIL)
}

