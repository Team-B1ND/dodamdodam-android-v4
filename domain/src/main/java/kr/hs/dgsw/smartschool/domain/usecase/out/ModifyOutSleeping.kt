package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.request.out.ModifyOutRequest
import kr.hs.dgsw.smartschool.domain.request.out.OutRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ModifyOutSleeping @Inject constructor(
    private val outRepository: OutRepository
) : BaseUseCase<ModifyOutSleeping.Params, OutItem>() {

    override fun invoke(params: Params): Flow<Resource<OutItem>> = execute {
        outRepository.modifyOutSleeping(request = ModifyOutRequest(params.endOutDate, params.outId, params.reason, params.startOutDate))
    }

    data class Params(
        val startOutDate: String,
        val endOutDate: String,
        val reason: String,
        val outId: Int
    )

}
