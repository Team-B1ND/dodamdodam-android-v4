package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import java.util.*

data class GetBusListState(
    val isLoading: Boolean = false,
    val busByDateList: List<BusByDate> = emptyList(),
    val error: String = ""
)
