package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetApplyBus @Inject constructor(
    private val busRepository: BusRepository
) : NoParamUseCase<Bus?>() {
    override fun invoke(): Flow<Resource<Bus?>> = execute {
        busRepository.getMyBus()
    }
}
