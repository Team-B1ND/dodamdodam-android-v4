package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.Student

fun StudentEntity.toModel(): Student = Student(
    classroom = Classroom(
        grade = this.grade,
        id = this.id,
        placeId = this.placeId,
        room = this.room,
    ),
    studentId = this.studentId,
    member = Member(
        this.email,
        this.memberId,
        this.joinDate,
        this.name,
        this.profileImage,
        this.role,
        this.status
    ),
    number = this.number,
    phone = this.phone
)

fun Student.toEntity(): StudentEntity = StudentEntity(
    studentId = this.studentId,
    grade = this.classroom.grade,
    id = this.classroom.id,
    placeId = this.classroom.placeId,
    room = this.classroom.grade,
    number = this.number,
    phone = this.phone,
    email = this.member.email,
    memberId = this.member.id,
    joinDate = this.member.joinDate ?: "",
    name = this.member.name,
    profileImage = this.member.profileImage ?: "",
    role = this.member.role,
    status = this.member.status
)
