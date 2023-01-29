package kr.hs.dgsw.smartschool.dodamdodam.features.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.setup.DataSetUpState
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setUpUseCases: SetUpUseCases
) : BaseViewModel() {

    private val isDataSetUpLoading = MutableLiveData(false)

    private val _dataSetUpState = MutableSharedFlow<DataSetUpState>()
    val dataSetUpState: SharedFlow<DataSetUpState> = _dataSetUpState

    init {
        combineLoadingVariable(isDataSetUpLoading)
    }

    fun dataSetUp() {
        setUpUseCases.dataSetUp().divideResult(
            isDataSetUpLoading,
            { viewModelScope.launch { _dataSetUpState.emit(DataSetUpState(result = it ?: "데이터 업데이트에 성공하였습니다.")) } },
            { viewModelScope.launch { _dataSetUpState.emit(DataSetUpState(error = it ?: "데이터를 업데이트 하지 못하였습니다.")) } }
        ).launchIn(viewModelScope)
    }
}
