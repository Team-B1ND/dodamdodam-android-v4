package kr.hs.dgsw.smartschool.dodamdodam.features.itmap

import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class ItMapViewModel: BaseViewModel() {

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }

}