package kr.hs.dgsw.smartschool.dodamdodam.features.bus

data class UpdateBusApplyState(
    val isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)
