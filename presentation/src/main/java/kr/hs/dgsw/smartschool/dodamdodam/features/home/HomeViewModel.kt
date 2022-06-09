package kr.hs.dgsw.smartschool.dodamdodam.features.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.dodamdodam.features.meal.MealState
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealUseCases: MealUseCases
) : BaseViewModel() {

    private val _mealState = MutableStateFlow(MealState(isLoading = false))
    val mealState: StateFlow<MealState> = _mealState

    fun getMealList(date: LocalDate) {
        mealUseCases.getAllMeal(
            date.year,
            date.monthValue
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _mealState.value = MealState(meal = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _mealState.value = MealState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealState.value = MealState(
                        error = result.message ?: "급식을 받아오지 못하였습니다."
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}