package kr.hs.dgsw.smartschool.dodamdodam.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.location.GetMyLocationState
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.MealState
import kr.hs.dgsw.smartschool.dodamdodam.features.setup.DataSetUpState
import kr.hs.dgsw.smartschool.domain.usecase.location.LocationUseCases
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetAllMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealUseCases: MealUseCases,
    private val locationUseCases: LocationUseCases,
    private val setUpUseCases: SetUpUseCases
) : BaseViewModel() {

    private val _mealState = MutableStateFlow(MealState(isLoading = false))
    val mealState: StateFlow<MealState> = _mealState

    private val _getMyLocationState = MutableStateFlow(GetMyLocationState())
    val getMyLocationState: StateFlow<GetMyLocationState> = _getMyLocationState

    private val _dataSetUpState = MutableStateFlow<DataSetUpState>(DataSetUpState())
    val dataSetUpState: StateFlow<DataSetUpState> = _dataSetUpState

    private val isDataSetUpLoading = MutableLiveData(false)
    private val isGetMealLoading = MutableLiveData(false)
    private val isGetMyLocationLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isDataSetUpLoading, isGetMealLoading, isGetMyLocationLoading)
        dataSetUp()
    }

    fun getMealList(date: LocalDate) {
        mealUseCases.getAllMeal(
            GetAllMeal.Params(
                date.year,
                date.monthValue
            )
        ).divideResult(
            { _mealState.value = MealState(meal = it ?: emptyList()) },
            { _mealState.value = MealState(error = it ?: "급식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun dataSetUp() {
        setUpUseCases.dataSetUp(Unit).divideResult(
            { _dataSetUpState.value = DataSetUpState(result = it) },
            { _dataSetUpState.value = DataSetUpState(error = it ?: "데이터를 업데이트 하지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun getMyLocation() {
        locationUseCases.getMyLocation(LocalDate.now().toString()).divideResult(
            { _getMyLocationState.value = GetMyLocationState(myLocations = it ?: emptyList()) },
            { _getMyLocationState.value = GetMyLocationState(error = it?: "위치를 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickSongMore() {
        viewEvent(ON_CLICK_SONG_MORE)
    }

    fun onClickMealMore() {
        viewEvent(ON_CLICK_MEAL_MORE)
    }

    fun onClickMenu() {
        viewEvent(ON_CLICK_MENU)
    }

    companion object {
        const val ON_CLICK_SONG_MORE = 0
        const val ON_CLICK_MEAL_MORE = 1
        const val ON_CLICK_MENU = 2
    }
}