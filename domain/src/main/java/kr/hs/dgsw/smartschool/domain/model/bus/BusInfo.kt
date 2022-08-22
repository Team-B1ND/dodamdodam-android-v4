package kr.hs.dgsw.smartschool.domain.model.bus

data class BusInfo(
    val idx: Int,
    val busName : String,
    val rideable : String,
    val peopleCount : String,
    val leaveTime : String
)
