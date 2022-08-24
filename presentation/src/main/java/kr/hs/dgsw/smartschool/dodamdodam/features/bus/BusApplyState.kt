package kr.hs.dgsw.smartschool.dodamdodam.features.bus

import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.util.*

data class BusApplyState(
    val isLoading: Boolean = false,
    val busId : Int = 0,
    val error: String = ""
)
