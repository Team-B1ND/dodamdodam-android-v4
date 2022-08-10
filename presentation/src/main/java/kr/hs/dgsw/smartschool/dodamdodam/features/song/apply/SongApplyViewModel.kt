package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply

import dagger.hilt.android.lifecycle.HiltViewModel
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import javax.inject.Inject

@HiltViewModel
class SongApplyViewModel @Inject constructor(
    private val songUseCases: SongUseCases
) : BaseViewModel() {

    fun onClickBack() {
        viewEvent(EVENT_ON_CLICK_BACK)
    }

    companion object {
        const val EVENT_ON_CLICK_BACK = 0
    }
}