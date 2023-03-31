package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity
import kr.hs.dgsw.smartschool.data.network.response.classroom.ClassroomPlaceResponse
import kr.hs.dgsw.smartschool.data.network.response.classroom.ClassroomResponse
import kr.hs.dgsw.smartschool.data.network.response.classroom.ClassroomTypeResponse
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.classroom.ClassroomPlace
import kr.hs.dgsw.smartschool.domain.model.classroom.ClassroomType

fun ClassroomEntity.toModel(): Classroom = Classroom(
    id = this.id,
    grade = this.grade,
    place = ClassroomPlace(
        id = this.id,
        name = this.placeName,
        type = ClassroomType(
            id = this.typeId,
            name = this.typeName
        )
    ),
    room = this.room
)

fun ClassroomResponse.toEntity(): ClassroomEntity = ClassroomEntity(
    id = this.id,
    grade = this.grade,
    room = this.room,
    placeId = this.place.id,
    placeName = this.place.name,
    typeId = this.place.type.id,
    typeName = this.place.type.name
)

fun ClassroomPlaceResponse.toModel(): ClassroomPlace = ClassroomPlace(
    id = this.id,
    name = this.name,
    type = this.type.toModel()
)

fun ClassroomTypeResponse.toModel(): ClassroomType = ClassroomType(
    id = this.id,
    name = this.name
)
