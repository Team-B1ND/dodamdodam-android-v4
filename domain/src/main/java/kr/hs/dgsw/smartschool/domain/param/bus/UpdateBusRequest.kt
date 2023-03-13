package kr.hs.dgsw.smartschool.domain.param.bus

data class UpdateBusRequest(
    val busName: String,
    val description: String,
    val leaveTime: String,
    val timeRequired: String,
    val peopleLimit: Int
)
