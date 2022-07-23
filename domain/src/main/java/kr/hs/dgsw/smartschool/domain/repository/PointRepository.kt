package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.point.MyYearPoint

interface PointRepository {
    suspend fun getMyPoint(year: String, type: Int): MyYearPoint
    suspend fun getMyPointTarget(target: Int): MyYearPoint
}