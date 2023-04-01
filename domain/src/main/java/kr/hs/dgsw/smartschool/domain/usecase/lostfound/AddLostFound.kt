package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.param.lostfound.LostFoundDataParam
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class AddLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : UseCase<LostFoundDataParam, String>() {

    override fun invoke(params: LostFoundDataParam): Flow<Resource<String>> = execute {
        lostFoundRepository.addLostFound(
            LostFoundDataParam(
                params.content,
                params.lostFoundId,
                params.picture,
                params.place,
                params.title,
                params.type
            )
        )
    }
}
