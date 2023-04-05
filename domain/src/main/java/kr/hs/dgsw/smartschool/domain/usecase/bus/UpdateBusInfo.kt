package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class UpdateBusInfo @Inject constructor(
    private val busRepository: BusRepository

) : UseCase<UpdateBusInfo.Params, String>() {
    data class Params(
        val id: Int,
        val request: UpdateBusParam
    )

    data class UpdateBusParam(
        val busName: String,
        val description: String,
        val leaveTime: String,
        val timeRequired: String,
        val peopleLimit: Int
    )

    override fun invoke(params: Params): Flow<Resource<String>> = execute {
        busRepository.updateBus(
            params.id,
            UpdateBusParam(
                busName = params.request.busName,
                description = params.request.description,
                leaveTime = params.request.leaveTime,
                timeRequired = params.request.timeRequired,
                peopleLimit = params.request.peopleLimit
            )
        )
        "버스 정보를 수정하였습니다."
    }
}
