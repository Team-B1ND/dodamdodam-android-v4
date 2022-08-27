package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom

class ClassInfoMapper : BaseEntityMapper<Classroom, ClassroomEntity> {
    override fun mapToModel(entity: ClassroomEntity): Classroom {
        return Classroom(
            entity.id,
            entity.grade,
            entity.placeId,
            entity.room
        )
    }

    override fun mapToEntity(model: Classroom): ClassroomEntity {
        return ClassroomEntity(
            model.id,
            model.grade,
            model.room,
            model.placeId
        )
    }
}
