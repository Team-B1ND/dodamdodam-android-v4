package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.domain.model.member.MemberAllowedType
import kr.hs.dgsw.smartschool.domain.model.member.Student

class StudentMapper : BaseEntityMapper<Student, StudentEntity> {

    override fun mapToModel(entity: StudentEntity): Student {
        return Student(
            entity.idx,
            entity.phone,
            entity.id,
            entity.name,
            entity.classroomIdx,
            entity.number,
            entity.email,
            entity.accessLevel,
            MemberAllowedType.fromInt(entity.allowed),
            null,
            entity.profileImage,
            entity.grade,
            entity.room,
            entity.placeIdx
        )
    }

    override fun mapToEntity(model: Student): StudentEntity {
        return StudentEntity(
            model.idx,
            model.phone,
            model.id,
            model.name,
            model.classroomIdx,
            model.number,
            model.email,
            model.accessLevel,
            model.allowed.value,
            model.profileImage,
            model.grade,
            model.room,
            model.placeIdx
        )
    }
}
