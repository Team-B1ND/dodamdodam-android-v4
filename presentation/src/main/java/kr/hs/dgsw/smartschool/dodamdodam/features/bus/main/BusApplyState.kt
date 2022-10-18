package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

data class BusApplyState(
    val isLoading: Boolean = false,
    val busId: Int = 0,
    val error: String = ""
)
