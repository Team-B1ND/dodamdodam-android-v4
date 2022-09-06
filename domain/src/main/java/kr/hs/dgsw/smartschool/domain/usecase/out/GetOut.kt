package kr.hs.dgsw.smartschool.domain.usecase.out

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.out.Out
import kr.hs.dgsw.smartschool.domain.model.out.OutItem
import kr.hs.dgsw.smartschool.domain.model.out.OutStatus
import kr.hs.dgsw.smartschool.domain.repository.OutRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetOut @Inject constructor(
    private val outRepository: OutRepository
) : BaseUseCase<GetOut.Params, Out>() {

    override fun invoke(params: Params): Flow<Resource<Out>> = execute {
        outRepository.getOut(params.year, params.month, params.status)
    }

    data class Params(
        val year: Int,
        val month: Int,
        val status: OutStatus
    )

}
