package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

data class BusState(
    val isLoading: Boolean = false,
    val busList: List<BusByDate> = emptyList(),
    val error: String = ""
)
