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

class GetMyPointTarget @Inject constructor(
    override val repository: PointRepository
): BaseUseCase<PointRepository>() {
    operator fun invoke(target: Int): Flow<Resource<MyPoint>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getMyPointTarget(target)
            emit(Resource.Success<MyPoint>(result))
        } catch (e: HttpException) {
            emit(Resource.Error(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error(Utils.NETWORK_ERROR_MESSAGE))
        }
    }
}