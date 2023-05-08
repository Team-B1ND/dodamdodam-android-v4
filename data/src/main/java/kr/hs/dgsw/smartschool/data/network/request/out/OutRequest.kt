package kr.hs.dgsw.smartschool.data.network.request.out

data class OutRequest(
    val endOutDate: String,
    val reason: String,
    val startOutDate: String
)
