package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.lostfound.ModifyCommentRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ModifyLostFoundComment @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<ModifyCommentRequest, String>() {

    override fun invoke(params: ModifyCommentRequest): Flow<Resource<String>> = execute {
        lostFoundRepository.modifyComment(
            params
        )
    }
}
