package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.base.BaseEntityMapper
import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.Student

class StudentMapper : BaseEntityMapper<Student, StudentEntity> {

    override fun mapToModel(entity: StudentEntity): Student {
        return Student(
            Classroom(
                grade = entity.grade,
                id = entity.id,
                placeId = entity.placeId,
                room = entity.room,
            ),
            entity.studentId,
            Member(
                entity.email,
                entity.memberId,
                entity.joinDate,
                entity.name,
                entity.profileImage,
                entity.role,
                entity.status
            ),
            entity.number,
            entity.phone
        )
    }

    override fun mapToEntity(model: Student): StudentEntity {
        return StudentEntity(
            model.studentId,
            model.classroom.grade,
            model.classroom.id,
            model.classroom.placeId,
            model.classroom.grade,
            model.number,
            model.phone,
            model.member.email,
            model.member.id,
            model.member.joinDate ?: "",
            model.member.name,
            model.member.profileImage ?: "",
            model.member.role,
            model.member.status
        )
    }
}
