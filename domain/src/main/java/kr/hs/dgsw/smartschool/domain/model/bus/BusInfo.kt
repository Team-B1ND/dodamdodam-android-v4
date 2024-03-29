package kr.hs.dgsw.smartschool.domain.model.bus

data class BusInfo(
    val idx: Int,
    val busName: String,
    val rideable: Boolean,
    val peopleCount: String,
    val leaveTime: String,
    val isSelected: Boolean = false
)
