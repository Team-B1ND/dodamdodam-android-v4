package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetCalorieOfMeal @Inject constructor(
    private val mealRepository: MealRepository
) : NoParamUseCase<String?>() {

    override fun invoke(): Flow<Resource<String?>> = execute {
        mealRepository.getCalorieOfMeal()
    }
}
