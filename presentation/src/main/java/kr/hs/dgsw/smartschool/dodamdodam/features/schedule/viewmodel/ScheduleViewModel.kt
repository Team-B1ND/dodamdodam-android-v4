package kr.hs.dgsw.smartschool.dodamdodam.features.schedule.viewmodel

import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class ScheduleViewModel : BaseViewModel() {


    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }

}