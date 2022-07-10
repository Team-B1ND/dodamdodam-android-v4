package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class SignUpViewModel: BaseViewModel() {

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
        val isNotMatchForm = id.value!!.length !in 5..20 || pw.value!!.length !in 7..20
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

