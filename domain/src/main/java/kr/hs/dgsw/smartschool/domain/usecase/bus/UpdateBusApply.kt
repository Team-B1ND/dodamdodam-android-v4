package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class UpdateBusApply @Inject constructor(
    private val busRepository: BusRepository
) : UseCase<UpdateBusApply.Param, String>() {
    override fun invoke(params: Param): Flow<Resource<String>> = execute {
        busRepository.updateBusApply(params.busId, params.originBusId)
        "버스 신청을 수정하였습니다."
    }

    data class Param(
        val busId: Int,
        val originBusId: Int
    )
}
