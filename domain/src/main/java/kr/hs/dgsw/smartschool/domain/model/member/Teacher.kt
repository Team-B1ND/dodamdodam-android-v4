package kr.hs.dgsw.smartschool.domain.model.member


data class Teacher(
    val id: Int,
    val member: Member,
    val phone: String,
    val position: String,
    val tel: String
)
