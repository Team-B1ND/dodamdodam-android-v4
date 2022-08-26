package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.UpdateBusApplyRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class UpdateBusApply @Inject constructor(
    private val busRepository: BusRepository
) : BaseUseCase<UpdateBusApplyRequest, String>() {
    override fun invoke(params: UpdateBusApplyRequest): Flow<Resource<String>> = execute {
        busRepository.updateBusApply(params)
        "버스 신청을 수정하였습니다."
    }
}
