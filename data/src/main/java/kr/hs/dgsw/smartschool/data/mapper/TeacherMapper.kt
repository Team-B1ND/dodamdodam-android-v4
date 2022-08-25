package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.TeacherEntity
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.Teacher

class TeacherMapper : BaseEntityMapper<Teacher, TeacherEntity> {

    override fun mapToModel(entity: TeacherEntity): Teacher {
        return Teacher(
            entity.teacherId,
            Member(
                entity.email,
                entity.memberId,
                entity.joinDate,
                entity.name,
                entity.profileImage,
                entity.role,
                entity.status
            ),
            entity.phone,
            entity.position,
            entity.tel
        )
    }

    override fun mapToEntity(model: Teacher): TeacherEntity {
        return TeacherEntity(
            model.id,
            model.member.email,
            model.member.id,
            model.member.joinDate,
            model.member.name,
            model.member.profileImage,
            model.member.role,
            model.member.status,
            model.phone,
            model.position,
            model.tel
        )
    }
}
