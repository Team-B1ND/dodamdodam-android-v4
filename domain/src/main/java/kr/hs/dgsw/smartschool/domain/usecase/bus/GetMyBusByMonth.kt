package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.bus.Bus
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetMyBusByMonth @Inject constructor(
    private val busRepository: BusRepository
) : UseCase<GetMyBusByMonth.Param, List<Bus>>() {
    override fun invoke(params: Param): Flow<Resource<List<Bus>>> = execute {
        busRepository.getMyBusByMonth(
            year = params.year,
            month = params.month
        )
    }
    data class Param(
        val year: Int,
        val month: Int
    )
}
