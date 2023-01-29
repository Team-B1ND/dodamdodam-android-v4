package kr.hs.dgsw.smartschool.domain.usecase.point

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyYearPointsUseCase @Inject constructor(
    val repository: PointRepository
) : UseCase<Int, List<Point>>() {

    override operator fun invoke(params: Int): Flow<Resource<List<Point>>> = execute {
        repository.getMyYearPoints(params)
    }
}
