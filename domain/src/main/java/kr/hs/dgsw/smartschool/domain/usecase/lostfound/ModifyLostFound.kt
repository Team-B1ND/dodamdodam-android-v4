package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.UseCase
import kr.hs.dgsw.smartschool.domain.param.lostfound.LostFoundDataRequest
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class ModifyLostFound @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : UseCase<LostFoundDataRequest, String>() {

    override fun invoke(params: LostFoundDataRequest): Flow<Resource<String>> = execute {
        lostFoundRepository.modifyLostFound(
            params
        )
    }
}
