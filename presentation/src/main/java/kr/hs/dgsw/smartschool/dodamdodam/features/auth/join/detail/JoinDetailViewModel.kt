package kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.join.state.JoinState
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.isNotEmailValid
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.isNotPhoneNumberValid
import kr.hs.dgsw.smartschool.domain.usecase.auth.JoinUseCase
import javax.inject.Inject

@HiltViewModel
class JoinDetailViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
) : BaseViewModel() {

    var id: String = ""
    var pw: String = ""

    private val _joinState = MutableSharedFlow<JoinState>()
    val joinState: SharedFlow<JoinState> = _joinState

    val email = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val room = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val isAgree = MutableLiveData(false)

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
        if (isNotAgree) {
            viewEvent(EVENT_NOT_AGREE)
            return
        }

        signUp()
    }

    private fun signUp() {
        joinUseCase(
            JoinUseCase.Params(
                email = email.value ?: return,
                grade = grade.value?.toInt() ?: return,
                id = id,
                name = name.value ?: return,
                number = number.value?.toInt() ?: return,
                phone = phone.value ?: return,
                pw = pw,
                room = room.value?.toInt() ?: return
            )
        ).divideResult(
            isLoading,
            { viewModelScope.launch { _joinState.emit(JoinState(result = it ?: "")) } },
            { viewModelScope.launch { _joinState.emit(JoinState(error = it ?: "회원가입에 실패했습니다.")) } }
        ).launchIn(viewModelScope)
    }

    companion object {
        const val EVENT_EMPTY = 1
        const val EVENT_NOT_MATCH_FORM = 2
        const val EVENT_NOT_PHONE_NUMBER = 3
        const val EVENT_NOT_EMAIL = 4
        const val EVENT_NOT_AGREE = 5
    }
}
