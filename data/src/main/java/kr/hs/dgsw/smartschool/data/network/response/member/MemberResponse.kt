package kr.hs.dgsw.smartschool.data.network.response.member

data class MemberResponse(
    val email: String,
    val id: String,
    val joinDate: String?,
    val name: String,
    val profileImage: String?,
    val role: String,
    val status: String,
    val student: StudentResponse
)
