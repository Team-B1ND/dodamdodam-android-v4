package kr.hs.dgsw.smartschool.domain.usecase.point

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyPoint @Inject constructor(
    val repository: PointRepository
) : BaseUseCase<GetMyPoint.Params, MyYearPoint>() {
    override operator fun invoke(params: Params): Flow<Resource<MyYearPoint>> = execute {
        repository.getMyPoint(params.year, params.type)
    }

    data class Params(
        val year: String,
        val type: Int
    )
}
