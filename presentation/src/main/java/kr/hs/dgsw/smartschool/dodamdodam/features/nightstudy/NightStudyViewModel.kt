package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.state.NightStudyState
import kr.hs.dgsw.smartschool.domain.usecase.nightgstudy.NightStudyUseCases
import javax.inject.Inject

@HiltViewModel
class NightStudyViewModel @Inject constructor(
    private val nightStudyUseCases: NightStudyUseCases
) : BaseViewModel() {

    private val _nightStudyState = MutableSharedFlow<NightStudyState>()
    val nightStudyState = _nightStudyState.asSharedFlow()

    private val isGetMyNightStudyLoading = MutableLiveData(false)
    private val isDeleteNightStudyLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isGetMyNightStudyLoading, isDeleteNightStudyLoading)
    }

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickNightStudyWrite() {
        viewEvent(ON_CLICK_NIGHT_STUDY_WRITE)
    }

    fun getMyNightStudy() {
        nightStudyUseCases.getMyNightStudy().divideResult(
            isGetMyNightStudyLoading,
            { nightStudyList -> viewModelScope.launch { _nightStudyState.emit(NightStudyState(isUpdate = true, nightStudyList = nightStudyList ?: emptyList())) } },
            { error -> viewModelScope.launch { _nightStudyState.emit(NightStudyState(error = error ?: "심자 목록을 불러오는데 실패했습니다.")) } }
        ).launchIn(viewModelScope)
    }

    fun deleteNightStudy(id: Int) {
        nightStudyUseCases.deleteNightStudy(id).divideResult(
            isDeleteNightStudyLoading,
            { message -> viewModelScope.launch { _nightStudyState.emit(NightStudyState(message = message ?: "심자 삭제에 성공했습니다.")) } },
            { error -> viewModelScope.launch { _nightStudyState.emit(NightStudyState(error = error ?: "심자 삭제에 실패했습니다.")) } }
        ).launchIn(viewModelScope)
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val ON_CLICK_NIGHT_STUDY_WRITE = 1
    }
}
