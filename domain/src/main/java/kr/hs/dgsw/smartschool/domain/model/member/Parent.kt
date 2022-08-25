package kr.hs.dgsw.smartschool.domain.model.member

data class Parent(
    val id: Int,
    val phone: String,
    val student: Student
)