package kr.hs.dgsw.smartschool.domain.usecase.location

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.location.Location
import kr.hs.dgsw.smartschool.domain.repository.LocationRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class PutLocation @Inject constructor(
    val repository: LocationRepository
) : BaseUseCase<PutLocation.Params, String>() {

    override fun invoke(params: Params): Flow<Resource<String>> = execute {
        repository.putLocation(params.idx, params.location)
    }

    data class Params(
        val idx: Int,
        val location: Location
    )
}
