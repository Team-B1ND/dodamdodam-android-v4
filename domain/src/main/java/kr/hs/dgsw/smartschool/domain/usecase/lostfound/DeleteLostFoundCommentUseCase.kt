package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteLostFoundCommentUseCase @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<DeleteLostFoundCommentUseCase.Params, String>() {

    data class Params(val commentIdx: Int)

    override fun invoke(params: Params): Flow<Resource<String>> = execute{
        lostFoundRepository.deleteComment(params.commentIdx)
        "분실물 댓글을 삭제하였습니다."
    }
}