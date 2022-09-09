package kr.hs.dgsw.smartschool.domain.request.bus

data class AddBusRequest(
    val busName: String,
    val description: String,
    val leaveTime: String,
    val timeRequired: String,
    val peopleLimit: Int
)
