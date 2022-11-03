package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate

data class BusTaskState(
    val isLoading: Boolean = false,
    val success : String = "",
    val error: String = ""
)
