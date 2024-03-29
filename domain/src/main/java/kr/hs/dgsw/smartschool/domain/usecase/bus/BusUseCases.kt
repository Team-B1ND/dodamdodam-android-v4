package kr.hs.dgsw.smartschool.domain.usecase.bus

data class BusUseCases(
    val getBus: GetBusList,
    val getMyBus: GetApplyBus,
    val getMyBusMonth: GetMyBusByMonth,
    val addBusApply: AddBusApply,
    val updateBusApply: UpdateBusApply,
    val deleteBusApply: DeleteBusApply,

    // 도담 티쳐
    val addBus: AddBus,
    val updateBusInfo: UpdateBusInfo,
    val deleteBus: DeleteBus
)
