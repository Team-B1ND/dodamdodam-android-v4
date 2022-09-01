package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class PostLostFoundComment @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<PostLostFoundComment.Params, String>() {
    data class Params(
        val comment: String,
        val lostFoundIdx: Int
    )

    override fun invoke(params: Params): Flow<Resource<String>> = execute{
        lostFoundRepository.postLostFoundComment(
            LostFoundDataRequest(
            params.comment,
            params.lostFoundIdx
        )
        )
    }
}