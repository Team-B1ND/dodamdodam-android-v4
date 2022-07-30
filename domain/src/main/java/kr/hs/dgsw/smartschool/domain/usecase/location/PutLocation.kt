package kr.hs.dgsw.smartschool.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PutLocation @Inject constructor(
    override val repository: LocationRepository
) : BaseUseCase<LocationRepository>() {
    operator fun invoke(params: Params): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.putLocation(params.idx, params.location)
            emit(Resource.Success<String>(result))
        } catch (e: HttpException) {
            emit(Resource.Error(Utils.convertErrorBody(e)))
        } catch (e: IOException) {
            emit(Resource.Error(Utils.NETWORK_ERROR_MESSAGE))
        }
    }

    data class Params(
        val idx: Int,
        val location: Location
    )
}