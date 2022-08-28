package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.LostFoundCommentPutRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class PutLostFoundCommentUseCase @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<PutLostFoundCommentUseCase.Params, String>() {


    data class Params(
        val comment: String,
        val lostFoundCommentIdx: Int
    )

    override fun invoke(params: Params): Flow<Resource<String>> = execute{
        lostFoundRepository.putLostFoundComment(
            LostFoundCommentPutRequest(
            params.comment,
            params.lostFoundCommentIdx
        )
        )
    }
}