package kr.hs.dgsw.smartschool.domain.usecase.bus

import kr.hs.dgsw.b1nd.dodamdodam.domain.usecase.bus.*

data class BusUseCases(
    val getBus: GetBus,
    val getMyBus : GetMyBus,
    val getMyBusMonth: GetMyBusMonth,
    val addBus: AddBus,
    val addBusApply: AddBusApply,
    val alterBusInfo: AlterBusInfo,
    val alterBusApply: AlterBusApply,
    val deleteBusApply: DeleteBusApply,
    val deleteBus: DeleteBus
)
