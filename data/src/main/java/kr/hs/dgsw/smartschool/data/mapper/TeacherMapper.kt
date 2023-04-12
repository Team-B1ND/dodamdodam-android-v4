package kr.hs.dgsw.smartschool.data.mapper

import kr.hs.dgsw.smartschool.data.database.entity.TeacherEntity
import kr.hs.dgsw.smartschool.data.network.response.member.TeacherResponse
import kr.hs.dgsw.smartschool.domain.model.member.Member
import kr.hs.dgsw.smartschool.domain.model.member.Teacher

fun TeacherEntity.toModel(): Teacher = Teacher(
    id = this.teacherId,
    member = Member(
        this.email,
        this.memberId,
        this.joinDate,
        this.name,
        this.profileImage,
        this.role,
        this.status,

    ),
    phone = this.phone,
    position = this.position,
    tel = this.tel
)

fun TeacherResponse.toModel(): Teacher = Teacher(
    id = this.id,
    member = Member(
        this.member.email,
        this.member.id,
        this.member.joinDate,
        this.member.name,
        this.member.profileImage,
        this.member.role,
        this.member.status
    ),
    phone = this.phone,
    position = this.position,
    tel = this.tel
)

fun TeacherResponse.toEntity(): TeacherEntity = TeacherEntity(
    teacherId = this.id,
    email = this.member.email,
    memberId = this.member.id,
    joinDate = this.member.joinDate,
    name = this.member.name,
    profileImage = this.member.profileImage,
    role = this.member.role,
    status = this.member.status,
    phone = this.phone,
    position = this.position,
    tel = this.tel
)
