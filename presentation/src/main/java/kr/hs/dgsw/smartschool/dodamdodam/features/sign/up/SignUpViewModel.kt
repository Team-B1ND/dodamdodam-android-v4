package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class SignUpViewModel: BaseViewModel() {

    companion object {
        const val EVENT_ON_CLICK_NEXT = 2
    }

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val pwRight = MutableLiveData<String>()

    fun onClickNext() {
        viewEvent(EVENT_ON_CLICK_NEXT)
    }

}

