package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.request.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyBusByMonth @Inject constructor(
    private val busRepository: BusRepository,
    private val request: MyBusByMonthRequest
): BaseUseCase<Unit, List<Bus>>() {
    override fun invoke(params: Unit): Flow<Resource<List<Bus>>> = execute {
        busRepository.getMyBusByMonth(request)
    }
}