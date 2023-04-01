package kr.hs.dgsw.smartschool.data.network.request.bus

data class UpdateBusApplyRequest(
    val busId: Int,
    val originBusId: Int
)
