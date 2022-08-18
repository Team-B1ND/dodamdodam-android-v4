package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddBusApply @Inject constructor(
    private val busRepository: BusRepository
) : BaseUseCase<Int, String>() {
    override operator fun invoke(params: Int): Flow<Resource<String>> = execute {
        busRepository.addBusApply(params).toString()
    }
}