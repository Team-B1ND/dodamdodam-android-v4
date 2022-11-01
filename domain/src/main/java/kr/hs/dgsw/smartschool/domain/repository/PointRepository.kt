package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.point.Point

interface PointRepository {

    suspend fun getMyYearPoints(year: Int): List<Point>
}
