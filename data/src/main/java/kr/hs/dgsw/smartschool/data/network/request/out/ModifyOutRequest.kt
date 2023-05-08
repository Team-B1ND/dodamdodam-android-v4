package kr.hs.dgsw.smartschool.data.network.request.out

data class ModifyOutRequest(
    val endOutDate: String,
    val outId: Int,
    val reason: String,
    val startOutDate: String
)
