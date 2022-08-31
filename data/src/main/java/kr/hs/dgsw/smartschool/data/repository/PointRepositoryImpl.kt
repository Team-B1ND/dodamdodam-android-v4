package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.PointDataSource
import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(
    private val pointDataSource: PointDataSource
) : PointRepository {

    override suspend fun getMyYearPoints(year: Int): List<MyYearPoint> {
        return pointDataSource.getMyYearPoints(year)
    }
}
