package kr.hs.dgsw.smartschool.dodamdodam.features.location

import kr.hs.dgsw.smartschool.domain.model.time.TimeTable

data class GetAllTimeState(
    val timeTable: List<TimeTable> = emptyList(),
    val error: String = ""
)
