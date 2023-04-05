package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ModifyLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : UseCase<ModifyLostFound.Param, String>() {

    override fun invoke(params: Param): Flow<Resource<String>> = execute {
        lostFoundRepository.modifyLostFound(
            params.content,
            params.lostFoundId,
            params.picture,
            params.place,
            params.title,
            params.type
        )
    }

    data class Param(
        val content: String,
        val lostFoundId: Int?,
        val picture: String,
        val place: String,
        val title: String,
        val type: String
    )
}
