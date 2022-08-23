package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class UpdateBusInfo @Inject constructor(
    private val busRepository: BusRepository

) : BaseUseCase<ModifyBusDtoRequest, String>() {
    override fun invoke(params: ModifyBusDtoRequest): Flow<Resource<String>> = execute {
        busRepository.updateBusInfo(params)
        "버스 정보를 수정하였습니다."
    }
}