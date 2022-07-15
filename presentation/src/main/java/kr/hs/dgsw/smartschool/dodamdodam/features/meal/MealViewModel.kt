package kr.hs.dgsw.smartschool.dodamdodam.features.meal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
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

    private val _mealState = MutableStateFlow(MealState(isLoading = false))
    val mealState: StateFlow<MealState> = _mealState

    private val _targetDate = MutableLiveData<LocalDate>()
    val targetDate: LiveData<LocalDate> get() = _targetDate

    private val todayDate by lazy {
         LocalDate.now()
    }

    init {
        _targetDate.value = LocalDate.now()
        getMealList()
    }

    /**
     * 급식 리스트를 받아오는 함수입니다.
     */
    fun getMealList() {
        mealUseCases.getAllMeal(
           _targetDate.value?.year ?: todayDate.year,
           _targetDate.value?.monthValue ?: todayDate.monthValue
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    isLoading.value = false
                    _mealState.value = MealState(meal = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    isLoading.value = true
                    _mealState.value = MealState(isLoading = true)
                }
                is Resource.Error -> {
                    isLoading.value = false
                    _mealState.value = MealState(
                        error = result.message ?: "급식을 받아오지 못하였습니다."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun setTargetDate(date: LocalDate) {
        _targetDate.value = date
    }

    fun onClickDate() = viewEvent(EVENT_CLICK_DATE)
    fun onClickPlusDate() {
        _targetDate.value?.let {
            if (it.plusDays(1).month != it.month) {
                _targetDate.value = it.plusDays(1)
                getMealList()
                return
            }
            _targetDate.value = it.plusDays(1)
            viewEvent(EVENT_UPDATE_DATE)
        }
    }
    fun onClickMinusDate() {
        _targetDate.value?.let {
            if (it.minusDays(1).month != it.month) {
                _targetDate.value = it.minusDays(1)
                getMealList()
                return
            }
            _targetDate.value = it.minusDays(1)
            viewEvent(EVENT_UPDATE_DATE)
        }
    }
}