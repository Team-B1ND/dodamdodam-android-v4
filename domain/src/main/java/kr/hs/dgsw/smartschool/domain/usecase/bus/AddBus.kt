package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.AddBusRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddBus @Inject constructor(
    private val busRepository: BusRepository
): BaseUseCase<AddBusRequest, String>() {
    override operator fun invoke(params:AddBusRequest): Flow<Resource<String>> = execute {
        busRepository.addBus(params)
        "버스를 추가하였습니다"
    }
}