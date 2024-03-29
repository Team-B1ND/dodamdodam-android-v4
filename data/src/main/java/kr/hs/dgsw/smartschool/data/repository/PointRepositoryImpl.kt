package kr.hs.dgsw.smartschool.data.repository

import kr.hs.dgsw.smartschool.data.datasource.PointDataSource
import kr.hs.dgsw.smartschool.data.mapper.toModel
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.repository.PointRepository
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(
    private val pointDataSource: PointDataSource
) : PointRepository {

    override suspend fun getMyYearPoints(year: Int): List<Point> {
        return pointDataSource.getMyYearPoints(year).map { pointResponse -> pointResponse.toModel() }
    }
}
