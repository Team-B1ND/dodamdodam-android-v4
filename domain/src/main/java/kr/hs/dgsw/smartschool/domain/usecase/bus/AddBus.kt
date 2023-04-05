package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddBus @Inject constructor(
    private val busRepository: BusRepository
) : UseCase<AddBus.Param, String>() {
    override operator fun invoke(params: Param): Flow<Resource<String>> = execute {
        busRepository.addBus(
            busName = params.busName,
            description = params.description,
            leaveTime = params.leaveTime,
            timeRequired = params.timeRequired,
            peopleLimit = params.peopleLimit
        )
        "버스를 추가하였습니다"
    }
    data class Param(
        val busName: String,
        val description: String,
        val leaveTime: String,
        val timeRequired: String,
        val peopleLimit: Int = 0
    )
}
