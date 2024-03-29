package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.NoParamUseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetLostFoundAll @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : NoParamUseCase<List<LostFound>>() {

    override fun invoke(): Flow<Resource<List<LostFound>>> = execute {
        lostFoundRepository.getLostFoundAll()
    }
}
