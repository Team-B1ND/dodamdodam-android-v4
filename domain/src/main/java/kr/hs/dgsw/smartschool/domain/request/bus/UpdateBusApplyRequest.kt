package kr.hs.dgsw.smartschool.domain.request.bus

data class UpdateBusApplyRequest(
    val busId: Int,
    val originBusId: Int
)
