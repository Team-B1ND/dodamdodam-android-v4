package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetBusList @Inject constructor(
    private val busRepository: BusRepository
) : BaseUseCase<Unit, BusByDate>() {
    override fun invoke(params: Unit): Flow<Resource<BusByDate>> = execute {
        busRepository.getBusList()
    }
}
