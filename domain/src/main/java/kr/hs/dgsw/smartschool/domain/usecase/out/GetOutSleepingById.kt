package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.out.OutSleeping
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetOutSleepingById @Inject constructor(
    private val outRepository: OutRepository
) : BaseUseCase<Int, OutSleeping>() {

    override fun invoke(outSleepingIdx: Int): Flow<Resource<OutSleeping>> = execute {
        outRepository.getOutSleepingById(outSleepingIdx)
    }
}
