package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ModifyLostFoundComment @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : UseCase<ModifyLostFoundComment.Param, String>() {

    override fun invoke(params: Param): Flow<Resource<String>> = execute {
        lostFoundRepository.modifyComment(
            params.comment,
            params.commentId
        )
    }

    data class Param(
        val comment: String,
        val commentId: Int
    )
}
