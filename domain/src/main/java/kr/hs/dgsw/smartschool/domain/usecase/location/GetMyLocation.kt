package kr.hs.dgsw.smartschool.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.location.LocationInfo
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyLocation @Inject constructor(
    val repository: LocationRepository
) : BaseUseCase<String, List<LocationInfo>>() {
    override fun invoke(params: String): Flow<Resource<List<LocationInfo>>> = execute {
        repository.getMyLocation(params)
    }
}
