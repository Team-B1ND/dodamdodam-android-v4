package kr.hs.dgsw.smartschool.dodamdodam.features.auth.join

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class JoinViewModel : BaseViewModel() {

    companion object {
        const val EVENT_ON_CLICK_NEXT = 0

        const val EVENT_EMPTY = 1
        const val EVENT_NOT_MATCH_FORM = 2
        const val EVENT_NOT_SAME_PW = 3
    }

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val pwRight = MutableLiveData<String>()

    fun onClickNext() {
        val isEmpty = id.value.isNullOrBlank() || pw.value.isNullOrBlank() || pwRight.value.isNullOrBlank()
        if (isEmpty) {
            viewEvent(EVENT_EMPTY)
            return
        }

        val idRegex = Regex("/^[a-zA-Z0-9]{5,20}\$/")
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{7,20}$")
        val isNotMatchForm = idRegex.matches(id.value ?: return).not() && passwordRegex.matches(pw.value ?: return).not()
        if (isNotMatchForm) {
            viewEvent(EVENT_NOT_MATCH_FORM)
            return
        }

        val isNotSamePw = pw.value!! != pwRight.value!!
        if (isNotSamePw) {
            viewEvent(EVENT_NOT_SAME_PW)
            return
        }

        viewEvent(EVENT_ON_CLICK_NEXT)
    }
}
