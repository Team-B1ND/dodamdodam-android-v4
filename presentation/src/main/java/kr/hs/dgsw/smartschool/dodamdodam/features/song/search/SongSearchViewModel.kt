package kr.hs.dgsw.smartschool.dodamdodam.features.song.search

import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel

class SongSearchViewModel : BaseViewModel() {

    val keyword = MutableLiveData<String>()

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    fun onClickSearch() {
        viewEvent(EVENT_ON_CLICK_SEARCH)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
        const val EVENT_ON_CLICK_SEARCH = 1
    }

}