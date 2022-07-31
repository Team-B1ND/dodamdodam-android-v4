package kr.hs.dgsw.smartschool.dodamdodam.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.location.GetMyLocationState
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.MealState
import kr.hs.dgsw.smartschool.dodamdodam.features.setup.DataSetUpState
import kr.hs.dgsw.smartschool.domain.usecase.location.LocationUseCases
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.usecase.setup.SetUpUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
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
            date.year,
            date.monthValue
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isGetMealLoading.value = false
                    _mealState.value = MealState(meal = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    isGetMealLoading.value = true
                    _mealState.value = MealState(isLoading = true)
                }
                is Resource.Error -> {
                    isGetMealLoading.value = false
                    _mealState.value = MealState(
                        error = result.message ?: "급식을 받아오지 못하였습니다."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun dataSetUp() {
        setUpUseCases.dataSetUp().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isDataSetUpLoading.value = false
                    _dataSetUpState.value = DataSetUpState(result = result.data)
                }
                is Resource.Loading -> {
                    isDataSetUpLoading.value = true
                }
                is Resource.Error -> {
                    isDataSetUpLoading.value = false
                    _dataSetUpState.value = DataSetUpState(error = result.message ?: "데이터를 업데이트 하지 못하였습니다.")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getMyLocation() {
        locationUseCases.getMyLocation(LocalDate.now().toString()).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isGetMyLocationLoading.value = false
                    _getMyLocationState.value = GetMyLocationState(myLocations = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    isGetMyLocationLoading.value = true
                }
                is Resource.Error -> {
                    isGetMyLocationLoading.value = false
                    _getMyLocationState.value = GetMyLocationState(
                        error = result.message ?: "위치를 받아오지 못하였습니다."
                    )
                }
            }
        }.launchIn(viewModelScope)
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