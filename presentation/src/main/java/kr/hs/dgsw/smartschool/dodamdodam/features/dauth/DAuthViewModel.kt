package kr.hs.dgsw.smartschool.dodamdodam.features.dauth

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import javax.inject.Inject

class DAuthViewModel : BaseViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun onClickSignIn() {
        if (id.value.isNullOrBlank() || pw.value.isNullOrBlank()) {
            return
        }
    }

    fun onClickJoin() {
        viewEvent(EVENT_ON_CLICK_JOIN)
    }

    companion object {
        const val EVENT_ON_CLICK_JOIN = 0
    }

}
