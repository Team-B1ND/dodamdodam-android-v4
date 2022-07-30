package kr.hs.dgsw.smartschool.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.request.LocationRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import kr.hs.dgsw.smartschool.domain.util.Utils
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostLocation @Inject constructor(
    override val repository: LocationRepository
) : BaseUseCase<LocationRepository>() {

    operator fun invoke(params: Params): Flow<Resource<String>> {
        val locationInfo = params.locationInfo.clone()
        locationInfo.time = null
        locationInfo.place = null

        val locations = ArrayList<LocationInfo>()
        locations.add(locationInfo)

        return flow {
            try {
                emit(Resource.Loading())
                val result = repository.postLocation(
                    LocationRequest(locations = locations)
                )
                emit(Resource.Success<String>(result))
            } catch (e: HttpException) {
                emit(Resource.Error(Utils.convertErrorBody(e)))
            } catch (e: IOException) {
                emit(Resource.Error(Utils.NETWORK_ERROR_MESSAGE))
            }
        }
    }

    data class Params(
        val locationInfo: LocationInfo
    )
}