package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.param.lostfound.AddCommentParam
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddLostFoundComment @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : UseCase<AddCommentParam, String>() {

    override fun invoke(params: AddCommentParam): Flow<Resource<String>> = execute {
        lostFoundRepository.addComment(
            params
        )
    }
}
