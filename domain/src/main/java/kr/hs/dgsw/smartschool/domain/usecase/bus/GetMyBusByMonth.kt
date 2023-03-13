package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.param.bus.MyBusByMonthRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyBusByMonth @Inject constructor(
    private val busRepository: BusRepository
) : UseCase<MyBusByMonthRequest, List<Bus>>() {
    override fun invoke(params: MyBusByMonthRequest): Flow<Resource<List<Bus>>> = execute {
        busRepository.getMyBusByMonth(params)
    }
}
