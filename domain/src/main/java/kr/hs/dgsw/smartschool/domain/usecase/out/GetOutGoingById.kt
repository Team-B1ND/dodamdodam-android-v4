package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.out.OutGoing
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetOutGoingById @Inject constructor(
    private val outRepository: OutRepository
) : BaseUseCase<Int, OutGoing>() {

    override fun invoke(params: Int): Flow<Resource<OutGoing>> = execute {
        outRepository.getOutGoingById(params)
    }
}
