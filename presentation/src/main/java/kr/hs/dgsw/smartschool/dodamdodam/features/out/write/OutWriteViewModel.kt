package kr.hs.dgsw.smartschool.dodamdodam.features.out.write

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class OutWriteViewModel : BaseViewModel() {

    val isOutGoing = MutableLiveData(true)
    val isOutSleeping = MutableLiveData(false)

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    fun selectOutGoing() {
        isOutGoing.value = true
        isOutSleeping.value = false
    }

    fun selectOutSleeping() {
        isOutGoing.value = false
        isOutSleeping.value = true
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }
}
