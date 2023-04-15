package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.data.network.response.member.MemberResponse
import kr.hs.dgsw.smartschool.data.network.response.member.StudentResponse
import kr.hs.dgsw.smartschool.domain.model.classroom.Classroom
import kr.hs.dgsw.smartschool.domain.model.classroom.ClassroomPlace
import kr.hs.dgsw.smartschool.domain.model.classroom.ClassroomType
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.Student

fun StudentEntity.toModel(): Student = Student(
    classroom = Classroom(
        grade = this.grade,
        id = this.id,
        place = ClassroomPlace(
            id = this.id,
            name = this.name,
            type = ClassroomType(
                id = this.id,
                name = this.name
            )
        ),
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
    placeId = this.classroom.place.id,
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

fun StudentResponse.toModel(): Student = Student(
    classroom = this.classroom,
    studentId = this.studentId,
    member = this.member.toModel(),
    number = this.number,
    phone = this.phone,
)

fun MemberResponse.toModel(): Member = Member(
    email = this.email,
    id = this.id,
    joinDate = this.joinDate,
    name = this.name,
    profileImage = this.profileImage,
    role = this.role,
    status = this.status
)
