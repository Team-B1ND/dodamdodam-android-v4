package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.request.OutRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteOutSleeping @Inject constructor(
    private val outRepository: OutRepository
) : BaseUseCase<Int, String>() {
    override fun invoke(outSleepingIdx: Int): Flow<Resource<String>> = execute {
        outRepository.deleteOutSleeping(outSleepingIdx)
    }
}