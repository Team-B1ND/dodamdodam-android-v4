package kr.hs.dgsw.smartschool.domain.model.response

data class UserData (
    val id: String,
    val generation: Int,
    val name: String,
    val email: String,
    val role: String,
    val status: Int,
    val profileImage: String
)