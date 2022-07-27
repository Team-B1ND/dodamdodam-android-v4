package kr.hs.dgsw.smartschool.dodamdodam.features.location

import kr.hs.dgsw.smartschool.domain.model.time.Time

data class GetAllTimeState(
    val timeTable: List<Time> = emptyList(),
    val error: String = ""
)
