package kr.hs.dgsw.smartschool.domain.model.bus

data class Bus(
    val id: Int,
    val busName: String,
    val description: String,
    val peopleLimit: Int,
    val leaveTime: String,
    val timeRequired: String,
    val peopleCount: Int
)
