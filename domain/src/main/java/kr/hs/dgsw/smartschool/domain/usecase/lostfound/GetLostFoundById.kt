package kr.hs.dgsw.smartschool.domain.usecase.lostfound

import kotlinx.coroutines.flow.Flow
import kr.hs.dgsw.smartschool.domain.base.BaseUseCase
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.repository.LostFoundRepository
import kr.hs.dgsw.smartschool.domain.util.Resource
import javax.inject.Inject

class GetLostFoundById @Inject constructor(
    private val lostFoundRepository: LostFoundRepository
) : BaseUseCase<Int, LostFound>() {

    override fun invoke(params: Int): Flow<Resource<LostFound>> = execute {
        lostFoundRepository.getLostFound(id = params)
    }
}
