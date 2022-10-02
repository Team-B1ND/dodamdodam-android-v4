package kr.hs.dgsw.smartschool.domain.request

data class UpdateBusRequest(
    val idx: Int,
    val busName: String,
    val description: String,
    val leaveTime: String,
    val timeRequired: String,
    val peopleLimit: Int
)
