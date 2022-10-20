package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.util.Resource

data class GetBusListState(
    val isLoading: Boolean = false,
    val bus: BusByDate? = null,
    val  success : String  = "",
    val error: String = ""
)
