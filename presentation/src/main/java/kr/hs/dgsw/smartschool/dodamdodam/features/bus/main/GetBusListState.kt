package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

data class GetBusListState(
    val isLoading: Boolean = false,
    val bus: BusByDate? = null,
    val applyBusId: Int = 0,
    val error: String = "",
)
