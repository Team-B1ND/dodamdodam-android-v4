package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMeal @Inject constructor(
    private val repository: MealRepository
) : UseCase<GetMeal.Params, Meal>() {

    override operator fun invoke(params: Params): Flow<Resource<Meal>> = execute {
        repository.getMeal(params.year, params.month, params.day)
    }

    data class Params(
        val year: Int,
        val month: Int,
        val day: Int
    )
}
