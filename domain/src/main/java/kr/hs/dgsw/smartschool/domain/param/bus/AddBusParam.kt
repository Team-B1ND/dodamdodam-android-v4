package kr.hs.dgsw.smartschool.domain.param.bus

data class AddBusParam(
    val busName: String,
    val description: String,
    val leaveTime: String,
    val timeRequired: String,
    val peopleLimit: Int = 0
)
