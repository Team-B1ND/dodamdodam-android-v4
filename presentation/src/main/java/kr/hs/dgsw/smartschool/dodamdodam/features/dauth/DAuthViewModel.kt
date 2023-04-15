package kr.hs.dgsw.smartschool.dodamdodam.features.dauth

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class DAuthViewModel : BaseViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun onClickSignIn() {
        if (id.value.isNullOrBlank() || pw.value.isNullOrBlank()) {
            return
        }
        viewEvent(EVENT_ON_CLICK_LOGIN)
    }

    fun onClickJoin() {
        viewEvent(EVENT_ON_CLICK_JOIN)
    }

    companion object {
        const val EVENT_ON_CLICK_JOIN = 0
        const val EVENT_ON_CLICK_LOGIN = 1
    }
}
