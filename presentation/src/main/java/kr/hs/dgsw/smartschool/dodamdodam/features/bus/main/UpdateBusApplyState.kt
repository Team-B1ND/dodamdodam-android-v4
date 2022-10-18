package kr.hs.dgsw.smartschool.dodamdodam.features.bus.main

data class UpdateBusApplyState(
    val isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)
