package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteBusApply @Inject constructor(
    private val busRepository: BusRepository,
    private val idx : Int
) : BaseUseCase<Unit, String>() {
    override fun invoke(params: Unit): Flow<Resource<String>> = execute {
        busRepository.deleteBusApply(idx).toString()
    }
}