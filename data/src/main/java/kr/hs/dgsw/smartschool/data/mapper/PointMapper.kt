package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.network.response.point.PointPlaceResponse
import kr.hs.dgsw.smartschool.data.network.response.point.PointResponse
import kr.hs.dgsw.smartschool.data.network.response.point.PointTypeResponse
import kr.hs.dgsw.smartschool.domain.model.point.Point
import kr.hs.dgsw.smartschool.domain.model.point.PointPlace
import kr.hs.dgsw.smartschool.domain.model.point.PointType

fun PointResponse.toModel(): Point = Point(
    reason = this.reason,
    idx = this.idx,
    score = this.score,
    student = this.student.toModel(),
    teacherIdx = this.teacher.toModel(),
    type = this.type.toModel(),
    given_date = this.given_date,
    target = this.target.toModel()
)

fun PointTypeResponse.toModel(): PointType = when (this.name) {
    PointTypeResponse.BONUS.name -> PointType.BONUS
    PointTypeResponse.MINUS.name -> PointType.MINUS
    PointTypeResponse.OFFSET.name -> PointType.OFFSET
    else -> PointType.MINUS
}

fun PointPlaceResponse.toModel(): PointPlace = when (this.name) {
    PointPlaceResponse.SCHOOL.name -> PointPlace.SCHOOL
    PointPlaceResponse.DORMITORY.name -> PointPlace.DORMITORY
    else -> PointPlace.DORMITORY
}
