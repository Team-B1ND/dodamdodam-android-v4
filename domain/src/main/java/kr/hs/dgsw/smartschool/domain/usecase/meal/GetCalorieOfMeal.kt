package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.meal.Calorie
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetCalorieOfMeal @Inject constructor(
    private val repository: MealRepository
) : UseCase<GetCalorieOfMeal.Params, String>() {

    override fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.getCalorieOfMeal(params.year, params.month, params.day)
    }

    data class Params(
        val year: Int,
        val month: Int,
        val day: Int
    )
}
