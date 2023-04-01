package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.param.out.OutParam
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ApplyOutSleeping @Inject constructor(
    private val outRepository: OutRepository
) : UseCase<ApplyOutSleeping.Params, OutItem>() {

    override fun invoke(params: Params): Flow<Resource<OutItem>> = execute {
        outRepository.applyOutSleeping(request = OutParam(params.endOutDate, params.reason, params.startOutDate))
    }

    data class Params(
        val startOutDate: String,
        val endOutDate: String,
        val reason: String
    )
}
