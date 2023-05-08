package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.state.DeleteEveningStudyState
import kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.state.GetMyEveningStudyState
import kr.hs.dgsw.smartschool.dodamdodam.features.out.state.GetOutByDateState
import kr.hs.dgsw.smartschool.domain.usecase.eveningstudy.EveningStudyUseCases
import javax.inject.Inject

@HiltViewModel
class EveningStudyViewModel @Inject constructor(
    private val eveningStudyUseCases: EveningStudyUseCases
) : BaseViewModel() {

    private val _getMyEveningStudyState = MutableStateFlow<GetMyEveningStudyState>(
        GetMyEveningStudyState()
    )

    private val isGetMyEveningStudyLoading = MutableLiveData(false)
    private val isDeleteEveningStudyLoading = MutableLiveData(false)
    private val _deleteEveningStudyState = MutableStateFlow<DeleteEveningStudyState>(
        DeleteEveningStudyState()
    )

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    fun onClickEveningStudyWrite() {
        viewEvent(ON_CLICK_EVENING_STUDY_WRITE)
    }

    fun getMyEveningStudy() {
        eveningStudyUseCases.getMyEveningStudy().divideResult(
            isGetMyEveningStudyLoading,
            { eveningStudyList -> _getMyEveningStudyState.value = GetMyEveningStudyState(isUpdate = true, eveningStudyList = eveningStudyList ?: emptyList() ) },
            { error -> _getMyEveningStudyState.value = GetMyEveningStudyState(error = error ?: "외출 목록을 불러오는데 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    fun deleteEveningStudy(id: Int) {
        eveningStudyUseCases.deleteEveningStudy(id).divideResult(
            isDeleteEveningStudyLoading,
            { message -> _deleteEveningStudyState.value = DeleteEveningStudyState(message = message ?: "외출 삭제에 성공했습니다.") },
            { error -> _deleteEveningStudyState.value = DeleteEveningStudyState(error = error ?: "외출 삭제에 실패했습니다.") }
        ).launchIn(viewModelScope)
    }

    companion object {
        const val ON_CLICK_BACK = 0
        const val ON_CLICK_EVENING_STUDY_WRITE = 1
    }
}
