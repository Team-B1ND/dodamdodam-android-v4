package kr.hs.dgsw.smartschool.data.network.response

data class Response<T>(
    val data: T,
    val status: Int,
    val message: String
)
