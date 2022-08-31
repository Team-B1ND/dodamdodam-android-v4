package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.Picture
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.request.LostFoundRequest
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class PostLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<PostLostFound.Params, String>() {

    data class Params(
        val type: Int,
        val title: String,
        val place: String?,
        val picture: List<Picture>?,
        val content: String
    )

    override fun invoke(params: Params): Flow<Resource<String>> = execute{
        lostFoundRepository.postCreateLostFound(
            LostFoundRequest(
                params.type,
                params.title,
                params.place,
                params.picture?.map { Picture(it.originalName, it.uploadName, it.type) },
                params.content
            )
        )
    }
}