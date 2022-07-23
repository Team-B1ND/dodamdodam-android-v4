package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.PointDataSource
import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(
    private val pointDataSource: PointDataSource
) : PointRepository {
    override suspend fun getMyPoint(year: String, type: Int): MyYearPoint =
        pointDataSource.getMyPoint(year, type)

    override suspend fun getMyPointTarget(target: Int): MyYearPoint =
        pointDataSource.getMyPointTarget(target)
}