package kr.hs.dgsw.smartschool.data.network.request.bus

data class UpdateBusRequest(
    val busName: String,
    val description: String,
    val leaveTime: String,
    val timeRequired: String,
    val peopleLimit: Int
)
