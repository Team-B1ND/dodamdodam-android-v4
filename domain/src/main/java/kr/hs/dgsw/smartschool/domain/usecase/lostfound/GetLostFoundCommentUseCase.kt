package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.Comment
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetLostFoundCommentUseCase @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<GetLostFoundCommentUseCase.Params, List<Comment>>() {

    data class Params(
        val lostFoundIdx: Int
    )

    override fun invoke(params: Params): Flow<Resource<List<Comment>>> = execute{
        lostFoundRepository.getComment(params.lostFoundIdx)
    }
}