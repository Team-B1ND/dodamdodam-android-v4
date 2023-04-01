package kr.hs.dgsw.smartschool.domain.param.out

data class ModifyOutParam(
    val endOutDate: String,
    val outId: Int,
    val reason: String,
    val startOutDate: String
)
