package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.base.noParamBaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetCalorieOfMeal @Inject constructor(
    private val mealRepository: MealRepository
) : noParamBaseUseCase<String?>() {

    override fun invoke(): Flow<Resource<String?>> = execute {
        mealRepository.getCalorieOfMeal()
    }
}
