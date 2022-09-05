package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class DeleteLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<DeleteLostFound.Params, String >() {

    data class Params(val idx: Int)

    override fun invoke(params: Params): Flow<Resource<String>> = execute{
        lostFoundRepository.deleteLostFound(params.idx)
        "분실 게시물을 삭제하였습니다."
    }
}