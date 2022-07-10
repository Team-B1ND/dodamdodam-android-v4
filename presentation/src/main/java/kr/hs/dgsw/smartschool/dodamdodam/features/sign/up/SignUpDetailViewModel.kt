package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.isNotEmailValid
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.isNotPhoneNumberValid
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
    private val isAgree = MutableLiveData(false)

    fun checkForm() {
        val isEmpty = grade.value.isNullOrBlank() || room.value.isNullOrBlank() || number.value.isNullOrBlank() ||
                name.value.isNullOrBlank() || phone.value.isNullOrBlank() || email.value.isNullOrBlank()
        if (isEmpty) {
            viewEvent(EVENT_EMPTY)
            return
        }

        val isNotMatchForm = name.value!!.length !in 2..10 || phone.value!!.length != 11 || email.value!!.length !in 10..30
        if (isNotMatchForm) {
            viewEvent(EVENT_NOT_MATCH_FORM)
            return
        }

        val isNotPhone = phone.value!!.isNotPhoneNumberValid()
        if (isNotPhone) {
            viewEvent(EVENT_NOT_PHONE_NUMBER)
            return
        }

        val isNotEmail = email.value!!.isNotEmailValid()
        if (isNotEmail) {
            viewEvent(EVENT_NOT_EMAIL)
            return
        }

        val isNotAgree = !isAgree.value!!
        if (isNotAgree){
            viewEvent(EVENT_NOT_AGREE)
            return
        }

        signUp()
    }

    private fun signUp() {
        Log.d("TestTest", "signUp: $id $pw ${email.value} ${phone.value} ${name.value} ${grade.value} ${room.value} ${number.value}")
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
                    isLoading.value = false
                    _signUpState.value = SignUpState(result = result.data ?: "")
                }
                is Resource.Loading -> {
                    isLoading.value = true
                    _signUpState.value = SignUpState(isLoading = true)
                }
                is Resource.Error -> {
                    isLoading.value = false
                    _signUpState.value = SignUpState(
                        error = result.message ?: "회원가입에 실패했습니다."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun changeAgree() {
        isAgree.value = isAgree.value?.not() ?: false
    }

    companion object {
        const val EVENT_EMPTY = 1
        const val EVENT_NOT_MATCH_FORM = 2
        const val EVENT_NOT_PHONE_NUMBER = 3
        const val EVENT_NOT_EMAIL = 4
        const val EVENT_NOT_AGREE = 5
    }
}