package kr.hs.dgsw.smartschool.domain.param.bus

data class UpdateBusApplyRequest(
    val busId: Int,
    val originBusId: Int
)
