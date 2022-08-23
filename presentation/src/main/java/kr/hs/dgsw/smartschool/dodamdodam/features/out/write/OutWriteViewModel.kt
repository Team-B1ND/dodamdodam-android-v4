package kr.hs.dgsw.smartschool.dodamdodam.features.out.write

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import java.util.*

class OutWriteViewModel : BaseViewModel() {

    val isOutGoing = MutableLiveData(true)
    val isOutSleeping = MutableLiveData(false)

    val startOutSleepingDate = MutableLiveData(Date())
    val endOutSleepingDate = MutableLiveData(Date())
    val startOutGoingDate = MutableLiveData(Date())

    val outReason = MutableLiveData<String>()

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

    fun onClickStartOutGoingDate() {
        viewEvent(EVENT_ON_CLICK_START_OUT_GOING_DATE)
    }
    fun onClickStartOutSleepingDate() {
        viewEvent(EVENT_ON_CLICK_START_OUT_SLEEPING_DATE)
    }
    fun onClickEndOutSleepingDate() {
        viewEvent(EVENT_ON_CLICK_END_OUT_SLEEPING_DATE)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
        const val EVENT_ON_CLICK_START_OUT_GOING_DATE = 1
        const val EVENT_ON_CLICK_START_OUT_SLEEPING_DATE = 2
        const val EVENT_ON_CLICK_END_OUT_SLEEPING_DATE = 3
    }
}
