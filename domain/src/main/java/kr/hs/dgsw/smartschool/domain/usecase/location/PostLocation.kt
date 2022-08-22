package kr.hs.dgsw.smartschool.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.request.LocationRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class PostLocation @Inject constructor(
    val repository: LocationRepository
) : BaseUseCase<PostLocation.Params, String>() {

    override operator fun invoke(params: Params): Flow<Resource<String>> = execute {
        val locationInfo = params.locationInfo.clone()
        locationInfo.time = null
        locationInfo.place = null

        val locations = ArrayList<LocationInfo>()
        locations.add(locationInfo)

        repository.postLocation(LocationRequest(locations = locations))
    }

    data class Params(
        val locationInfo: LocationInfo
    )
}
