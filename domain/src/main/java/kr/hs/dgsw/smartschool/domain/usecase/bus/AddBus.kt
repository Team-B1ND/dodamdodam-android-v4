package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.param.bus.AddBusParam
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddBus @Inject constructor(
    private val busRepository: BusRepository
) : UseCase<AddBusParam, String>() {
    override operator fun invoke(params: AddBusParam): Flow<Resource<String>> = execute {
        busRepository.addBus(params)
        "버스를 추가하였습니다"
    }
}
