package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetAllMeal @Inject constructor(
    private val repository: MealRepository
): BaseUseCase<GetAllMeal.Params, List<Meal>>() {

    override operator fun invoke(params: Params): Flow<Resource<List<Meal>>> = execute {
        repository.getAllMeal(params.year, params.month)
    }

    data class Params(
        val year: Int,
        val month: Int
    )
}