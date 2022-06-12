package kr.hs.dgsw.smartschool.dodamdodam.features.sign.up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class SignUpViewModel: BaseViewModel() {

    companion object {
        const val EVENT_ON_CLICK_NEXT = 2
    }

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    private val _pw = MutableLiveData<String>()
    val pw: LiveData<String> get() = _pw

    private val _pwRight = MutableLiveData<String>()
    val pwRight: LiveData<String> get() = _pwRight

    fun onClickNext() {
        viewEvent(EVENT_ON_CLICK_NEXT)
    }

}

