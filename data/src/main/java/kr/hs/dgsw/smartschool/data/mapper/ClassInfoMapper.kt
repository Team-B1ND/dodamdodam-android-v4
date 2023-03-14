package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

fun ClassroomEntity.toModel(): Classroom = Classroom(
    id = this.id,
    grade = this.grade,
    placeId = this.placeId,
    room = this.room
)

fun Classroom.toEntity(): ClassroomEntity = ClassroomEntity(
    id = this.id,
    grade = this.grade,
    placeId = this.placeId,
    room = this.room
)
