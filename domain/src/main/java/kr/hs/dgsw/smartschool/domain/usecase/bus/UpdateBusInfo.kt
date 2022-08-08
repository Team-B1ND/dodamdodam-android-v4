package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class UpdateBusInfo @Inject constructor(
    private val busRepository: BusRepository
) : BaseUseCase<Unit, String>() {
    override fun invoke(params: Unit): Flow<Resource<String>> = execute {
        busRepository.updateBusInfo()
    }
}