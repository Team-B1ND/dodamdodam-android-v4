package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.UpdateBusRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class UpdateBusInfo @Inject constructor(
    private val busRepository: BusRepository

) : BaseUseCase<UpdateBusInfo.Params, String>() {
    data class Params(
        val id : Int,
        val request: UpdateBusRequest
            )
    override fun invoke(params: Params): Flow<Resource<String>> = execute {
        busRepository.updateBus(params.id,params.request)
        "버스 정보를 수정하였습니다."
    }
}
