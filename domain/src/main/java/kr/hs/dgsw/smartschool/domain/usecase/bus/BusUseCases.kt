package kr.hs.dgsw.smartschool.domain.usecase.bus

import kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus.*


data class BusUseCases(
    val getBus: GetBusList,
    val getMyBus : GetMyBus,
    val getMyBusMonth: GetMyBusByMonth,
    val addBus: AddBus,
    val addBusApply: AddBusApply,
    val updateBusInfo: UpdateBusInfo,
    val updateBusApply: UpdateBusApply,
    val deleteBusApply: DeleteBusApply,
    val deleteBus: DeleteBus
)
