package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ApplyOutGoing @Inject constructor(
    private val outRepository: OutRepository
) : UseCase<ApplyOutGoing.Params, OutItem>() {

    override fun invoke(params: Params): Flow<Resource<OutItem>> = execute {
        outRepository.applyOutGoing(params.endOutDate, params.reason, params.startOutDate)
    }

    data class Params(
        val startOutDate: String,
        val endOutDate: String,
        val reason: String
    )
}
