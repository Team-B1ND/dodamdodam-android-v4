package kr.hs.dgsw.smartschool.domain.usecase.meal

import kotlinx.coroutines.flow.*
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DeleteMeal @Inject constructor(
    override val repository: MealRepository
): BaseUseCase<MealRepository>() {
    operator fun invoke() : Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading<String>())
            repository.deleteAllMeal()
            emit(Resource.Success<String>("급식을 삭제하였습니다."))
        } catch (e: HttpException) {
            emit(Resource.Error<String>(e.localizedMessage ?: "알 수 없는 오류가 발생했습니다."))
        } catch (e: IOException) {
            emit(Resource.Error<String>("서버에 도달할 수 없습니다. 네트워크 상태를 확인해 주세요."))
        }
    }
}