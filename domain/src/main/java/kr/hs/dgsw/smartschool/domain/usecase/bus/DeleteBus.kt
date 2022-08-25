package kr.hs.dgsw.smartschool.domain.usecase.bus

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.BusRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteBus @Inject constructor(
    private val busRepository: BusRepository

) : BaseUseCase<Int, String>() {
    override operator fun invoke(params: Int): Flow<Resource<String>> = execute {
        busRepository.deleteBus(params)
        "버스를 삭제하였습니다"
    }
}
