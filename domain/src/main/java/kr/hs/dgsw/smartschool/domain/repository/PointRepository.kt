package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.point.MyPoint

interface PointRepository {
    suspend fun getMyPoint(year: String, type: Int): MyPoint
    suspend fun getMyPointTarget(target: Int): MyPoint
}