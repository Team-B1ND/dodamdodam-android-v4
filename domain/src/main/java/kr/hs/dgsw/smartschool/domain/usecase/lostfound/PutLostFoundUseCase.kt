package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.lostfound.LostFoundRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class PutLostFoundUseCase @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<PutLostFoundUseCase.Params, String>() {


    data class Params(
        val idx: Int,
        val type: Int,
        val title: String,
        val place: String?,
        val picture: List<Picture>?,
        val content: String
    )

    override fun invoke(params: Params): Flow<Resource<String>> = execute{
        lostFoundRepository.modifyLostFound(
            LostFoundRequest(
                params.idx,
                params.type,
                params.title,
                params.place,
                params.picture?.map { Picture(it.originalName, it.uploadName, it.type) },
                params.content
            )
        )
    }
}