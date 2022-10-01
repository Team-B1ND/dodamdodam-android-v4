package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.lostfound.AddCommentRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddLostFoundComment @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<AddCommentRequest, String>() {

    override fun invoke(params: AddCommentRequest): Flow<Resource<String>> = execute {
        lostFoundRepository.addComment(
            params
        )
    }
}
