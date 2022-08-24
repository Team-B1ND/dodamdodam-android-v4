package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.request.OutRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class PostOutGoing @Inject constructor(
    private val outRepository: OutRepository
) : BaseUseCase<PostOutGoing.Params, String>() {

    override fun invoke(params: Params): Flow<Resource<String>> = execute {
        outRepository.postOutGoing(request = OutRequest(params.startTime, params.endTime, params.reason))
    }

    data class Params(
        val startTime: String,
        val endTime: String,
        val reason: String
    )
}
