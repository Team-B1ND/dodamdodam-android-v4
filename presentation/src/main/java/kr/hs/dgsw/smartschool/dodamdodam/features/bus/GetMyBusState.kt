package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import java.util.*

data class GetMyBusState(
    val isLoading: Boolean = false,
    val busList: List<Bus> = emptyList(),
    val error: String = ""
)
