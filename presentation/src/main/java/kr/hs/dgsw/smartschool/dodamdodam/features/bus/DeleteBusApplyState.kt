package kr.hs.dgsw.smartschool.dodamdodam.features.bus

data class DeleteBusApplyState(
    val isLoading: Boolean = false,
    val success: String = "",
    val error: String = ""
)
