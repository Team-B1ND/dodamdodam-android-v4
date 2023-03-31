package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.Point.PointPlaceResponse
import kr.hs.dgsw.smartschool.data.network.response.Point.PointResponse
import kr.hs.dgsw.smartschool.data.network.response.Point.PointTypeResponse
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointType

fun PointResponse.toModel(): Point = Point(
    reason = this.reason,
    idx = this.idx,
    score = this.score,
    studentIdx = this.studentIdx,
    teacherIdx = this.teacherIdx,
    type = this.type.toModel(),
    target = this.target.toModel()
)

fun PointTypeResponse.toModel(): PointType = when (this.name) {
    PointType.BONUS.name -> PointType.BONUS
    PointType.MINUS.name -> PointType.MINUS
    PointType.OFFSET.name -> PointType.OFFSET
    else -> PointType.MINUS
}

fun PointPlaceResponse.toModel(): PointPlace = when (this.name) {
    PointPlace.SCHOOL.name -> PointPlace.SCHOOL
    PointPlace.DORMITORY.name -> PointPlace.DORMITORY
    else -> PointPlace.DORMITORY
}
