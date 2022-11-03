package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

data class BusTaskState(
    val isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)
