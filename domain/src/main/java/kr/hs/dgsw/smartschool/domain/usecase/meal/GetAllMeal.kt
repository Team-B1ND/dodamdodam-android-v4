package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.*
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
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
            emit(Resource.Loading())
            val result = repository.getAllMeal(year, month)
            emit(Resource.Success<List<Meal>>(result))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "알 수 없는 오류가 발생했습니다."))
        } catch (e: IOException) {
            emit(Resource.Error("서버에 도달할 수 없습니다. 네트워크 상태를 확인해 주세요."))
        }
    }
}