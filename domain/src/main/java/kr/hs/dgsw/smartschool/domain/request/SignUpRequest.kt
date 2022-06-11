package kr.hs.dgsw.smartschool.domain.request

data class SignUpRequest(
    val id:String,
    val pw: String,
    val name: String,
    val phone: String,
    val email:String,
    val generation: Int,
    val role:String,
    val grade: Int,
    val classNum: Int,
    val stuNum : Int
)
