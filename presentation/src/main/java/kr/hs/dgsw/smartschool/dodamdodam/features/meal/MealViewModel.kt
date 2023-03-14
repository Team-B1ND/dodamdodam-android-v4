package kr.hs.dgsw.smartschool.dodamdodam.features.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.state.GetMealCalorieState
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.state.GetMealState
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mealUseCases: MealUseCases
) : BaseViewModel() {

    companion object {
        const val EVENT_CLICK_DATE = 1
        const val EVENT_UPDATE_DATE = 2
    }

    private val _getMealState = MutableStateFlow(GetMealState())
    val getMealState: StateFlow<GetMealState> = _getMealState

    private val _getMealCalorieState = MutableStateFlow(GetMealCalorieState())
    val getMealCalorieState: StateFlow<GetMealCalorieState> = _getMealCalorieState

    private val _targetDate = MutableLiveData<LocalDate>()
    val targetDate: LiveData<LocalDate> get() = _targetDate

    private val todayDate by lazy {
        LocalDate.now()
    }

    init {
        _targetDate.value = LocalDate.now()
        getMeal()
        getMealCalorie()
    }

    /**
     * 급식 리스트를 받아오는 함수입니다.
     */
    fun getMeal() {
        mealUseCases.getMeal(
            GetMeal.Params(
                _targetDate.value?.year ?: todayDate.year,
                _targetDate.value?.monthValue ?: todayDate.monthValue,
                _targetDate.value?.dayOfMonth ?: todayDate.dayOfMonth
            )
        ).divideResult(
            isLoading,
            { _getMealState.value = GetMealState(meal = it, isUpdate = true) },
            { _getMealState.value = GetMealState(error = it ?: "급식을 받아오지 못하였습니다.") }
        ).launchIn(viewModelScope)
    }

    private fun getMealCalorie() {
        mealUseCases.getCalorieOfMeal().divideResult(
            isLoading,
            { _getMealCalorieState.value = GetMealCalorieState(isUpdate = true, calorie = it) },
            { _getMealCalorieState.value = GetMealCalorieState(error = it ?: "칼로리를 받아오지 못했습니다.") }
        ).launchIn(viewModelScope)
    }

    fun setTargetDate(date: LocalDate) {
        _targetDate.value = date
    }

    fun onClickDate() = viewEvent(EVENT_CLICK_DATE)

    fun onClickPlusDate() {
        _targetDate.value?.let {
            _targetDate.value = it.plusDays(1)
            getMeal()
            viewEvent(EVENT_UPDATE_DATE)
        }
    }
    fun onClickMinusDate() {
        _targetDate.value?.let {
            _targetDate.value = it.minusDays(1)
            getMeal()
            viewEvent(EVENT_UPDATE_DATE)
        }
    }
}
