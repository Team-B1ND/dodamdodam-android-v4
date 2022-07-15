package kr.hs.dgsw.smartschool.domain.usecase.point

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.point.MyPoint
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMyPoint @Inject constructor(
    override val repository: PointRepository
): BaseUseCase<PointRepository>() {
    operator fun invoke(year: String, type: Int): Flow<Resource<MyPoint>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getMyPoint(year, type)
            emit(Resource.Success<MyPoint>(result))
        } catch (e: HttpException) {
            emit(Resource.Error(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error(Utils.NETWORK_ERROR_MESSAGE))
        }
    }
}