package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.*
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMeal @Inject constructor(
    override val repository: MealRepository
): BaseUseCase<MealRepository>() {
    operator fun invoke(
        year: Int, month: Int
    ): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading<List<Meal>>())
            val result = repository.getAllMeal(year, month)
            emit(Resource.Success<List<Meal>>(result))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Meal>>(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error<List<Meal>>(Utils.NETWORK_ERROR_MESSAGE))
        }
    }
}