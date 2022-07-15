package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.TeacherEntity
import kr.hs.dgsw.smartschool.domain.model.member.MemberAllowedType
import kr.hs.dgsw.smartschool.domain.model.member.Teacher

class TeacherMapper : BaseEntityMapper<Teacher, TeacherEntity> {
    override fun mapToModel(entity: TeacherEntity): Teacher {
        return Teacher(
            entity.idx,
            entity.phone,
            entity.id,
            entity.name,
            entity.email,
            entity.accessLevel,
            MemberAllowedType.fromInt(entity.allowed),
            null,
            entity.profileImage,
            entity.role
        )
    }

    override fun mapToEntity(model: Teacher): TeacherEntity {
        return TeacherEntity(
            model.idx,
            model.phone,
            model.id,
            model.name,
            model.email,
            model.accessLevel,
            model.allowed.value,
            model.profileImage,
            model.role
        )
    }

}